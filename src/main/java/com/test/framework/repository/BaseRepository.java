package com.test.framework.repository;

import java.io.Serializable;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable>
		extends PagingAndSortingRepository<T, ID>,QueryDslPredicateExecutor<T> {

	void sharedCustomMethod(ID id);
}