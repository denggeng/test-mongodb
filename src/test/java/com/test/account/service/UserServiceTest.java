package com.test.account.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.SdmAppStarter;
import com.test.account.domain.AdminSecurityUser;
import com.test.account.domain.Menu;
import com.test.account.domain.Role;
import com.test.account.domain.User;
import com.test.account.repository.RoleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SdmAppStarter.class)
public class UserServiceTest {

	@Autowired
	MenuService menuService;

	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepository;

	@Test
	public void testUpdateAdminUser() {
		Iterable<Menu> menus = menuService.findAll();

		Set<Menu> ms = new HashSet<>();
		for (Menu m : menus) {
			ms.add(m);
		}

		Role r = null;

		r = roleRepository.findByName("管理员");
		if (r == null) {
			r = new Role();
			r.setName("管理员");
			r.setComment("管理员");
		}
		r.setMenus(ms);
		roleRepository.save(r);

		User user = userService.findByUsername("dg@summba.com");
		user.getRoles().add(r);
		user.setPassword("111111");
		user.setNickName("庚庚");
		userService.encodePasswordAndSave(user);

		User user2 = userService.findByUsername("dg@summba.com");
		System.out.println(user2);
	}

	@Test
	public void testAdminUser() {
		User user = userService.findByUsername("dg@summba.com");
		AdminSecurityUser asu = new AdminSecurityUser(user);
		System.out.println(asu.getMenuMap());
		System.out.println(asu.getMenus());
	}

	@Test
	public void testAddUser() {

		Role r = null;
		r = new Role();
		r.setName("管理员");
		r.setComment("管理员");
		roleRepository.save(r);

		User user = new User();
		user.getRoles().add(r);
		user.setUsername("dg@summba.com");
		user.setPassword("111111");
		user.setNickName("庚庚");
		userService.encodePasswordAndSave(user);

		User user2 = userService.findByUsername("dg@summba.com");
		System.out.println(user2);
		Assert.assertTrue("dg@summba.com".equals(user2.getUsername()));
	}
}
