/**
 * 
 */
package com.test.account.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.test.framework.domain.AuditDocument;

/**
 * 权限表
 * 
 * @author Kenny
 * @time 2016年7月14日 下午4:14:24
 */
@Document
public class Permission extends AuditDocument {

	@Indexed(unique = true)
	private String name;

	private String displayName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
