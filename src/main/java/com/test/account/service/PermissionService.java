/**
 * 
 */
package com.test.account.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.account.domain.Permission;
import com.test.account.repository.PermissionRepository;
import com.test.framework.service.BaseService;

/**
 * @author Kenny 2016年7月17日 上午9:36:25
 */
@Service
public class PermissionService extends BaseService<Permission, String, PermissionRepository> {

	public List<Permission> findByName(String name) {
		return repository.findByName(name);
	}
}
