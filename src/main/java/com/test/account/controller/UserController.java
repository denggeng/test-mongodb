/**
 * 
 */
package com.test.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.account.domain.User;
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

	@RequestMapping("list")
	public String list(Model model) {
		Iterable<User> list = userService.findAll();
		model.addAttribute("list", list);
		return "account/user-list";
	}

	@RequestMapping("addSimpleUser")
	@ResponseBody
	public String addSimpleUser(User user) {
		userService.save(user);
		return "add success!";
	}
}
