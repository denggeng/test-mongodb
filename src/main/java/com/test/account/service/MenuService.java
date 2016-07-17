/**
 * 
 */
package com.test.account.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.account.domain.Menu;
import com.test.account.repository.MenuRepository;
import com.test.framework.service.BaseService;

/**
 * @author Kenny 2016年7月17日 上午9:36:25
 */
@Service
public class MenuService extends BaseService<Menu, String, MenuRepository> {

	public List<Menu> findByParentId(String parentId){
		return repository.findByParentId(parentId);
	}
}
