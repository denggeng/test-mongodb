package com.test.account.repository;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import com.test.account.domain.Role;
import com.test.framework.repository.BaseRepository;

@Component
public interface RoleRepository extends BaseRepository<Role, String>, QueryDslPredicateExecutor<Role> {

	List<Role> findByName(String name);
}
