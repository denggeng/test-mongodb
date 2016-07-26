/**
 * 
 */
package com.test.account.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
		if (StringUtils.isEmpty(menu.getId())) {
			menu.setId(null);
		} else {
			// 处理旧的子菜单
			Menu oldMenu = super.find(menu.getId());
			menu.setChildren(oldMenu.getChildren());
			// 处理旧的父菜单
			// 如果旧的父菜单不等于新的父菜单，则证明改了父菜单
			if (!oldMenu.getParentId().equals(menu.getParentId())) {
				// 找到旧的父菜单
				Menu oldpMenu = super.find(oldMenu.getParentId());
				if (oldpMenu != null) {
					Set<Menu> oldpChildren = oldpMenu.getChildren();
					// List<Menu> oldpNewChildren = new ArrayList<>();
					oldpChildren.remove(oldMenu);
					super.save(oldpMenu);
				}
			}
		}
		// 处理子菜单
		super.save(menu);
		// 处理父菜单
		Menu pMenu = super.find(menu.getParentId());
		if (pMenu != null) {
			pMenu.getChildren().add(menu);
			super.save(pMenu);
		}

	}

}
