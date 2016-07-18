package com.test.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.account.domain.Role;
import com.test.account.service.RoleService;

/**
 * @author Kenny 2016年7月18日 下午10:04:21
 */

@Controller
@RequestMapping("role")
public class RoleController {

	@Autowired
	RoleService roleService;

	@RequestMapping("list")
	public String list(Model model) {
		Iterable<Role> list = roleService.findAll();
		model.addAttribute("list", list);
		return "account/role-list";
	}

	@RequestMapping("add")
	public String add(Model model) {
		model.addAttribute("entity", new Role());
		return "account/role-form";
	}

	@RequestMapping("edit")
	public String edit(Model model, String id) {
		model.addAttribute("entity", roleService.find(id));
		return "account/role-form";
	}

	@RequestMapping("save")
	public String save(Model model, Role entity) {
		roleService.save(entity);
		return "redirect:/role/list";
	}
}
