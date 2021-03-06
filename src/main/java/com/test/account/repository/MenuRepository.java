package com.test.account.repository;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import com.test.account.domain.Menu;
import com.test.framework.repository.BaseRepository;

@Component
public interface MenuRepository extends BaseRepository<Menu, String>, QueryDslPredicateExecutor<Menu> {

	List<Menu> findByParentId(String parentId);

}
