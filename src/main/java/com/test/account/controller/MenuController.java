/**
 * 
 */
package com.test.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping("addSimpleMenu")
	@ResponseBody
	public String addSimpleMenu(Menu menu) {
		menuService.save(menu);
		return "add success!";
	}
}
