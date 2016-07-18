package com.test.account.controller.editor;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.account.domain.Permission;
import com.test.account.service.PermissionService;

@Component
public class PermissionListEditor extends PropertyEditorSupport {
	@Autowired
	private PermissionService permissionService;

	/**
	 * Back From Page
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] ids = StringUtils.split(text, ",");
		List<Permission> permissions = new ArrayList<Permission>();
		for (String id : ids) {
			Permission permission = permissionService.find(id);
			permissions.add(permission);
		}
		setValue(permissions);
	}

	/**
	 * Set to page
	 */
	@Override
	public String getAsText() {
		@SuppressWarnings("unchecked")
		List<Permission> list = (List<Permission>) getValue();
		String s = "";
		for (Permission p : list) {
			s += "," + p.getId();
		}
		return s.replaceFirst(",", "");
	}
}
