package com.test.account.controller.editor;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.account.domain.Menu;
import com.test.account.service.MenuService;

@Component
public class MenuListEditor extends PropertyEditorSupport {
	@Autowired
	private MenuService menuService;

	/**
	 * Back From Page
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] ids = StringUtils.split(text, ",");
		List<Menu> menus = new ArrayList<Menu>();
		for (String id : ids) {
			Menu menu = menuService.find(id);
			menus.add(menu);
		}
		setValue(menus);
	}

	/**
	 * Set to page
	 */
	@Override
	public String getAsText() {
		@SuppressWarnings("unchecked")
		List<Menu> menus = (List<Menu>) getValue();
		String s = "";
		for (Menu m : menus) {
			s += "," + m.getId();
		}
		return s.replaceFirst(",", "");
	}
}
