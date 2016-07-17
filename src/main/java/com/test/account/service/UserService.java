/**
 * 
 */
package com.test.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.account.domain.User;
import com.test.account.repository.UserRepository;
import com.test.framework.service.BaseService;

/**
 * @author Kenny
 * @time 2016年7月14日 下午3:39:21
 */
@Service
public class UserService extends BaseService<User, String, UserRepository> {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/**
	 * @param userName
	 * @return
	 */
	public User findByUsername(String userName) {
		return repository.findByUsername(userName);
	}

	public void encodePasswordAndSave(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		super.save(user);
	}

}
