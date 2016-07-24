/**
 * 
 */
package com.test.account.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
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

	@Indexed(unique = true)
	private String name;

	private String comment;

	@DBRef
	private List<Permission> permissions = new ArrayList<>();

	@DBRef
	private List<Menu> menus = new ArrayList<>();

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
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", comment=" + comment + ", permissions=" + permissions + ", menus=" + menus
				+ ", getLastModifiedDate()=" + getLastModifiedDate() + ", getCreatedDate()=" + getCreatedDate()
				+ ", getId()=" + getId() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Role) {
			Role p = (Role) obj;
			if (p.getId().equals(this.getId())) {
				return true;
			}
		}
		return false;
	}

}
