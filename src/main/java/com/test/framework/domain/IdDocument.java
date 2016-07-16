/**
 * 
 */
package com.test.framework.domain;

import org.springframework.data.annotation.Id;

/**
 * 包含的ID属性的父类
 * 
 * @author Kenny
 * @time 2016年7月14日 下午3:43:46
 */

public abstract class IdDocument {

	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
