/**
 * 
 */
package com.test.account.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.account.domain.User;
import com.test.account.service.RoleService;
import com.test.account.service.UserService;

/**
 * @author Kenny
 * @time 2016年7月14日 下午3:41:02
 */
@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	@RequestMapping("list")
	public String list(Model model) {
		Iterable<User> list = userService.findAll();
		model.addAttribute("list", list);
		return "account/user-list";
	}

	@RequestMapping("add")
	public String add(Model model) {
		model.addAttribute("entity", new User());
		model.addAttribute("allRoles", roleService.findAll());
		model.addAttribute("list", userService.findAll());
		return "account/user-form";
	}

	@RequestMapping("edit")
	public String edit(Model model, String id) {
		model.addAttribute("entity", userService.find(id));
		model.addAttribute("allRoles", roleService.findAll());
		model.addAttribute("list", userService.findAll());
		return "account/user-form";
	}

	@RequestMapping("save")
	public String save(Model model, User entity) {
		if (StringUtils.isBlank(entity.getId())) {
			entity.setId(null);
		}
		userService.save(entity);

		return "redirect:/user/list";
	}
}
