/**
 * 
 */
package com.test.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.account.domain.User;
import com.test.account.repository.UserRepository;

/**
 * @author Kenny
 * @time 2016年7月14日 下午3:39:21
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * @param userName
	 * @return
	 */
	public User findByUsername(String userName) {
		return userRepository.findByUsername(userName);
	}

}
