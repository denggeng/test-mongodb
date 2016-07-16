/**
 * 
 */
package com.test.account.repository;

import java.math.BigInteger;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import com.test.account.domain.User;
import com.test.framework.repository.BaseRepository;

/**
 * @author Kenny
 * @time 2016年7月14日 下午3:39:54
 */
@Component
public interface UserRepository extends BaseRepository<User, BigInteger>, QueryDslPredicateExecutor<User> {

	User findByUsername(String username);

}
