/**
 * 
 */
package com.test.account.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import com.test.account.domain.User;
import com.test.framework.repository.BaseRepository;

/**
 * @author Kenny
 * @time 2016年7月14日 下午3:39:54
 */
@Component
public interface UserRepository extends BaseRepository<User, String>, QueryDslPredicateExecutor<User> {

	User findByUsername(String username);

}
