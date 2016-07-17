package com.test.account.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import com.test.account.domain.Role;
import com.test.framework.repository.BaseRepository;

@Component
public interface RoleRepository extends BaseRepository<Role, String>, QueryDslPredicateExecutor<Role> {

}
