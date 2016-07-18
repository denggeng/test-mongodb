/**
 * 
 */
package com.test.account.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.account.domain.Role;
import com.test.account.repository.RoleRepository;
import com.test.framework.service.BaseService;

/**
 * @author Kenny 2016年7月17日 上午9:36:25
 */
@Service
public class RoleService extends BaseService<Role, String, RoleRepository> {

	public List<Role> findByName(String name) {
		return repository.findByName(name);
	}
}
