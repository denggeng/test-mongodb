package com.test.account.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import com.test.account.domain.Permission;
import com.test.framework.repository.BaseRepository;

@Component
public interface PermissionRepository extends BaseRepository<Permission, String>, QueryDslPredicateExecutor<Permission> {

}
