package com.test.account.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AdminSecurityUser extends User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Menu> menus = new ArrayList<>();

	private List<Menu> rootMenus = new ArrayList<>();

	private Map<String, Menu> menuMap = new HashMap<>();

	public AdminSecurityUser(User user) {
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setNickName(user.getNickName());
		this.setRoles(user.getRoles());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		List<Role> userRoles = this.getRoles();

		if (userRoles != null) {
			for (Role role : userRoles) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
				authorities.add(authority);
			}
		}
		// 管理用户
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_admin");
		authorities.add(authority);
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public List<Menu> getMenus() {
		if (menus.size() == 0) {
			for (Role role : this.getRoles()) {
				List<Menu> ms = role.getMenus();
				menus.addAll(ms);
			}
		}
		return menus;
	}

	public Map<String, Menu> getMenuMap() {
		if (menuMap.size() == 0) {
			for (Menu m : getMenus()) {
				menuMap.put(m.getId(), m);
			}
		}
		return menuMap;
	}

	public List<Menu> getRootMenus() {
		if (rootMenus.size() == 0) {
			for (Menu m : getMenus()) {
				if ("root".equals(m.getParentId())) {
					rootMenus.add(m);
					// 构建授权的子菜单
					createAuthorizedChildrenMenuList(m);
				}
			}
			Collections.sort(rootMenus);
		}
		return rootMenus;
	}

	private void createAuthorizedChildrenMenuList(Menu rootMenu) {
		for (Menu m : rootMenu.getChildrenMenuList()) {
			if (getMenuMap().containsKey(m.getId())) {
				rootMenu.getAuthorizedChildrenMenuList().add(m);
				createAuthorizedChildrenMenuList(m);
			}
		}
	}

}
