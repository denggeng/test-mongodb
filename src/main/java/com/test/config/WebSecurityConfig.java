package com.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.test.account.security.LoginSuccessHandler;
import com.test.account.service.AdminUserDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

	// Code5----------------------------------------------
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}

	/**
	 * 用于管理的权限配置
	 * 
	 * @author denggeng
	 * @time 2016年6月6日 下午5:30:47
	 */
	@Configuration
	public static class AdminWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Autowired
		private AdminUserDetailsService adminUserDetailsService;

		@Autowired
		private BCryptPasswordEncoder bCryptPasswordEncoder;

		@Autowired
		private LoginSuccessHandler adminLoginSuccessHandler;

		@Override
		public void configure(WebSecurity web) throws Exception {//
			// 跳过静态文件
			web.ignoring().antMatchers("/admin/static/**",
					"/console/**"/* , "/admin/**" 暂时跳过登录界面 */);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// 允许所有用户访问”/”和”/home”

			http.authenticationProvider(this.createAuthenticationProvider()).authorizeRequests()
					.antMatchers("/css/**", "/img/**", "/js/**", "/register", "/getCaptcha", "/submitRegister",
							"/isLogin", "/jobs", "/about")
					.permitAll()
					// 其他地址的访问均需验证权限
					.anyRequest().authenticated().and().headers().frameOptions().sameOrigin().and().formLogin()
					// 指定登录页是”/login”
					.loginPage("/login").permitAll()
					// 登录成功后可使用loginSuccessHandler()存储用户信息，可选。
					.successHandler(adminLoginSuccessHandler)// code3
					.and().logout()
					// 退出登录后的默认网址是”/home”
					.logoutSuccessUrl("/").permitAll().invalidateHttpSession(true).and()
					// 登录后记住用户，下次自动登录
					// 数据库中必须存在名为persistent_logins的表
					// 建表语句见code15
					.rememberMe().tokenValiditySeconds(1209600);
			// 指定记住登录信息所使用的数据源
			// .tokenRepository(tokenRepository());// code4

		}

		@Bean(name = "adminAuthenticationProvider")
		public AuthenticationProvider createAuthenticationProvider() {
			DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
			dap.setUserDetailsService(adminUserDetailsService);
			dap.setPasswordEncoder(bCryptPasswordEncoder);
			return dap;
		}

	}

}
