/**
 * 
 */
package com.test.account.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.test.framework.domain.AuditDocument;

/**
 * 角色实体
 * 
 * @author Kenny
 * @time 2016年7月14日 下午4:16:58
 */

@Document
public class Role extends AuditDocument {

	private String name;

	private String comment;

	@DBRef
	private List<Permission> permissions;

	@DBRef
	private List<Menu> Menus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<Menu> getMenus() {
		return Menus;
	}

	public void setMenus(List<Menu> menus) {
		Menus = menus;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", comment=" + comment + ", permissions=" + permissions + ", Menus=" + Menus
				+ ", getLastModifiedDate()=" + getLastModifiedDate() + ", getCreatedDate()=" + getCreatedDate()
				+ ", getId()=" + getId() + "]";
	}

}
