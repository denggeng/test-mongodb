package com.test.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.account.domain.Permission;
import com.test.account.service.PermissionService;

/**
 * @author Kenny 2016年7月18日 下午10:04:21
 */

@Controller
@RequestMapping("permission")
public class PermissionController {

	@Autowired
	PermissionService permissionService;

	@RequestMapping("list")
	public String list(Model model) {
		Iterable<Permission> list = permissionService.findAll();
		model.addAttribute("list", list);
		return "account/permission-list";
	}

	@RequestMapping("add")
	public String add(Model model) {
		model.addAttribute("entity", new Permission());
		return "account/permission-form";
	}

	@RequestMapping("edit")
	public String edit(Model model, String id) {
		model.addAttribute("entity", permissionService.find(id));
		return "account/permission-form";
	}

	@RequestMapping("save")
	public String save(Model model, Permission entity) {
		permissionService.save(entity);
		return "redirect:/permission/list";
	}
}
