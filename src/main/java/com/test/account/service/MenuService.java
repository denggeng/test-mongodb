/**
 * 
 */
package com.test.account.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.test.account.domain.Menu;
import com.test.account.repository.MenuRepository;
import com.test.framework.service.BaseService;

/**
 * @author Kenny 2016年7月17日 上午9:36:25
 */
@Service
public class MenuService extends BaseService<Menu, String, MenuRepository> {

	public List<Menu> findByParentId(String parentId) {
		return repository.findByParentId(parentId);
	}

	@Override
	public void save(Menu menu) {
		if (StringUtils.isBlank(menu.getId())) {
			menu.setId(null);
		} else {
			Menu oldMenu = super.find(menu.getId());
			menu.setChildrenMenuList(oldMenu.getChildrenMenuList());
			// 处理旧的父菜单
			if (!oldMenu.getParentId().equals(menu.getParentId())) {
				Menu oldpMenu = super.find(oldMenu.getParentId());
				List<Menu> oldpChildrenMenuList = oldpMenu.getChildrenMenuList();
				// List<Menu> oldpNewChildren = new ArrayList<>();
				oldpChildrenMenuList.remove(oldMenu);
				super.save(oldpMenu);
			}
		}
		// 处理子菜单
		super.save(menu);
		// 处理父菜单
		Menu pMenu = super.find(menu.getParentId());
		if (pMenu != null) {
			pMenu.getChildrenMenuList().add(menu);
			super.save(pMenu);
		}

	}

}
