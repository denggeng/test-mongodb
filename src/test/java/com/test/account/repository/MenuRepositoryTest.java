/**
 * 
 */
package com.test.account.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.SdmAppStarter;
import com.test.account.domain.Menu;
import com.test.account.service.MenuService;

/**
 * @author Kenny 2016年7月17日 上午9:30:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SdmAppStarter.class)
public class MenuRepositoryTest {

	@Autowired
	MenuService menuService;

	@Test
	public void test() {
		Menu menu = new Menu();
		menu.setName("test");
		menu.setUrl("menu");
		menuService.save(menu);
		String id = menu.getId();
		Assert.assertNotNull(id);

		System.out.println("menu:" + menu);

		menu.setName("test2");

		menuService.save(menu);
		Menu menu2 = menuService.find(id);

		System.out.println("menu2:" + menu2);

		Assert.assertEquals(menu2.getName(), "test2");

		menuService.delete(id);

		Menu menu3 = menuService.find(id);
		System.out.println("menu3:" + menu3);

		Assert.assertNull(menu3);
	}

	@Test
	public void createThirdLevelMenu() {
		List<Menu> rootList = menuService.findByParentId("root");
		for (Menu m : rootList) {
			if ("用户管理".equals(m.getName())) {
				if (m.getChildrenMenuList().size() == 0) {
					Menu chdMenu = new Menu();
					chdMenu.setParentId(m.getId());
					chdMenu.setName("用户列表");
					chdMenu.setUrl("index");
					chdMenu.setOrderId(1l);

					Menu chdMenu2 = new Menu();
					chdMenu2.setParentId(m.getId());
					chdMenu2.setName("新增用户");
					chdMenu2.setUrl("user/add");
					chdMenu2.setOrderId(1l);
					menuService.save(chdMenu2);
					chdMenu.getChildrenMenuList().add(chdMenu2);
					menuService.save(chdMenu);
					m.getChildrenMenuList().add(chdMenu);
				} else {
					Menu chdMenu = m.getChildrenMenuList().get(0);
					if (chdMenu.getChildrenMenuList().size() == 0) {
						Menu chdMenu2 = new Menu();
						chdMenu2.setParentId(m.getId());
						chdMenu2.setName("新增用户");
						chdMenu2.setUrl("user/add");
						chdMenu2.setOrderId(1l);
						menuService.save(chdMenu2);
						chdMenu.getChildrenMenuList().add(chdMenu2);
					}
					m.getChildrenMenuList().add(chdMenu);
				}
				menuService.save(m);
			}
		}
	}

}
