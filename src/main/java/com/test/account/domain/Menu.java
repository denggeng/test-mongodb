
package com.test.account.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.common.collect.Lists;
import com.test.framework.domain.IdDocument;

@Document
public class Menu extends IdDocument implements Comparable<Menu>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 父菜单ID */
	private java.lang.String parentId;
	/** 名称 */
	private java.lang.String name;
	/** URL(#表示展开) */
	private java.lang.String url;
	/** 排序标识 */
	private java.lang.Long orderId;
	/** 目标窗口 */
	private java.lang.String target;

	/* 菜单图标样式 */
	private String iconClass;

	// 子菜单
	@DBRef
	private Set<Menu> children = new HashSet<>();

	// 授权的子菜单
	// @Transient
	@DBRef
	private List<Menu> authorizedChildrenMenuList = Lists.newArrayList();

	public void setParentId(java.lang.String value) {
		this.parentId = value;
	}

	public java.lang.String getParentId() {
		return this.parentId;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setUrl(java.lang.String value) {
		this.url = value;
	}

	public java.lang.String getUrl() {
		return this.url;
	}

	public void setOrderId(java.lang.Long value) {
		this.orderId = value;
	}

	public java.lang.Long getOrderId() {
		return this.orderId;
	}

	public java.lang.String getTarget() {
		return target;
	}

	public void setTarget(java.lang.String target) {
		this.target = target;
	}

	public List<Menu> getAuthorizedChildrenMenuList() {
		return authorizedChildrenMenuList;
	}

	public void setAuthorizedChildrenMenuList(List<Menu> authorizedChildrenMenuList) {
		this.authorizedChildrenMenuList = authorizedChildrenMenuList;
	}

	@Override
	public int compareTo(Menu o) {
		return (this.getOrderId() == null ? (Long) 0l : this.getOrderId())
				.compareTo(o.getOrderId() == null ? (Long) 0l : o.getOrderId());
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Menu) {
			Menu p = (Menu) obj;
			if (p.getId().equals(this.getId())) {
				return true;
			}
		}
		return false;
	}

	public Set<Menu> getChildren() {
		return children;
	}

	public void setChildren(Set<Menu> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Menu [parentId=" + parentId + ", name=" + name + ", url=" + url + ", orderId=" + orderId + ", target="
				+ target + ", iconClass=" + iconClass + ", children=" + children + ", authorizedChildrenMenuList="
				+ authorizedChildrenMenuList + ", getId()=" + getId() + "]";
	}

}
