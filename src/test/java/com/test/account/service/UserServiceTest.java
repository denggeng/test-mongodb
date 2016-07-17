package com.test.account.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.SdmAppStarter;
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

		List<Menu> ms = new ArrayList<>();
		for (Menu m : menus) {
			ms.add(m);
		}

		Role r = new Role();
		r.setName("管理员");
		r.setComment("管理员");
		r.setMenus(ms);
		
		roleRepository.save(r);

		User user = userService.findByUsername("dg@summba.com");
		user.setRoles(Collections.singletonList(r));
		userService.save(user);
		
		User user2 = userService.findByUsername("dg@summba.com");
		System.out.println(user2);
	}

}
