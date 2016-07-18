/**
 * 
 */
package com.test.account.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.account.domain.Menu;
import com.test.account.service.MenuService;

/**
 * @author Kenny 2016年7月17日 上午9:33:35
 */
@Controller
@RequestMapping("menu")
public class MenuController {

	@Autowired
	MenuService menuService;

	@RequestMapping("list")
	public String list(Model model) {
		Iterable<Menu> list = menuService.findAll();
		model.addAttribute("list", list);
		return "account/menu-list";
	}

	@RequestMapping("add")
	public String add(Model model) {
		model.addAttribute("entity", new Menu());
		model.addAttribute("list", menuService.findAll());
		return "account/menu-form";
	}

	@RequestMapping("edit")
	public String edit(Model model, String id) {
		model.addAttribute("entity", menuService.find(id));
		model.addAttribute("list", menuService.findAll());
		return "account/menu-form";
	}

	@RequestMapping("save")
	public String save(Model model, Menu entity) {
		if (StringUtils.isBlank(entity.getId())) {
			entity.setId(null);
		} else {
			Menu oldMenu = menuService.find(entity.getId());
			entity.setChildrenMenuList(oldMenu.getChildrenMenuList());
		}
		// 处理子菜单
		menuService.save(entity);
		// 处理父菜单
		Menu pMenu = menuService.find(entity.getParentId());
		if (pMenu != null) {
			pMenu.getChildrenMenuList().add(entity);
			menuService.save(pMenu);
		}
		return "redirect:/menu/list";
	}
}
