/**
 * 
 */
package com.test.account.domain;

import java.util.List;

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

	@DBRef
	private List<Role> roles;

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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", nickName=" + nickName + ", password=" + password + ", roles=" + roles
				+ ", getLastModifiedDate()=" + getLastModifiedDate() + ", getCreatedDate()=" + getCreatedDate()
				+ ", getId()=" + getId() + "]";
	}

}
