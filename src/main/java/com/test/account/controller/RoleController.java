package com.test.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.account.controller.editor.MenuListEditor;
import com.test.account.controller.editor.PermissionListEditor;
import com.test.account.domain.Role;
import com.test.account.service.MenuService;
import com.test.account.service.PermissionService;
import com.test.account.service.RoleService;

/**
 * @author Kenny 2016年7月18日 下午10:04:21
 */

@Controller
@RequestMapping("role")
public class RoleController {

	@Autowired
	RoleService roleService;

	@Autowired
	MenuService menuService;

	@Autowired
	PermissionService permissionService;

	@Autowired
	private PermissionListEditor permissionListEditor;

	@Autowired
	private MenuListEditor menuListEditor;

	@InitBinder
	public void initBinder(WebDataBinder b) {
		b.registerCustomEditor(List.class, "menus", menuListEditor);
		b.registerCustomEditor(List.class, "permissions", permissionListEditor);
	}

	@RequestMapping("list")
	public String list(Model model) {
		Iterable<Role> list = roleService.findAll();
		model.addAttribute("list", list);
		return "account/role-list";
	}

	@RequestMapping("add")
	public String add(Model model) {
		model.addAttribute("entity", new Role());
		model.addAttribute("allMenus", menuService.findAll());
		model.addAttribute("allPermissions", permissionService.findAll());
		return "account/role-form";
	}

	@RequestMapping("edit")
	public String edit(Model model, String id) {
		model.addAttribute("allMenus", menuService.findAll());
		model.addAttribute("allPermissions", permissionService.findAll());
		model.addAttribute("entity", roleService.find(id));
		return "account/role-form";
	}

	@RequestMapping("save")
	public String save(Model model, Role entity) {
		roleService.save(entity);
		return "redirect:/role/list";
	}
}
