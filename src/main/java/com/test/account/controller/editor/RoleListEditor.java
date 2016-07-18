package com.test.account.controller.editor;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.account.domain.Role;
import com.test.account.service.RoleService;

/**
 * 用于转换用户表单中复杂对象Role的checkbox的关联。
 * 
 * @author calvin
 */
@Component
public class RoleListEditor extends PropertyEditorSupport {

	@Autowired
	private RoleService roleService;

	/**
	 * Back From Page
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] ids = StringUtils.split(text, ",");
		List<Role> roles = new ArrayList<Role>();
		for (String id : ids) {
			Role Role = roleService.find(id);
			roles.add(Role);
		}
		setValue(roles);
	}

	/**
	 * Set to page
	 */
	@Override
	public String getAsText() {
		@SuppressWarnings("unchecked")
		List<Role> roles = (List<Role>) getValue();
		String s = "";
		for (Role r : roles) {
			s += "," + r.getId();
		}
		return s.replaceFirst(",", "");
	}
}
