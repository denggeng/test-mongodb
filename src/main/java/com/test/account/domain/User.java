/**
 * 
 */
package com.test.account.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.test.framework.domain.AuditDocument;

/**
 * 用户实体
 * 
 * @author Kenny
 * @time 2016年7月14日 下午3:37:20
 */
@Document
public class User extends AuditDocument {

	@Indexed(unique = true)
	private String username;

	private String nickName;

	private String password;

	private boolean forbidden;

	@DBRef
	private Set<Role> roles = new HashSet<>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof User) {
			User p = (User) obj;
			if (p.getId().equals(this.getId())) {
				return true;
			}
		}
		return false;
	}

	public boolean isForbidden() {
		return forbidden;
	}

	public void setForbidden(boolean forbidden) {
		this.forbidden = forbidden;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", nickName=" + nickName + ", password=" + password + ", forbidden="
				+ forbidden + ", roles=" + roles + ", getLastModifiedDate()=" + getLastModifiedDate()
				+ ", getCreatedDate()=" + getCreatedDate() + ", getId()=" + getId() + "]";
	}

}
