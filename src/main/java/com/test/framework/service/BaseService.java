package com.test.framework.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.Predicate;
import com.test.framework.repository.BaseRepository;

public abstract class BaseService<T, ID extends Serializable, R extends BaseRepository<T, ID>> {
	protected static Logger logger = LoggerFactory.getLogger(BaseService.class);

	protected Class<T> entityClass;

	@Autowired
	protected R repository;

	public T find(ID id) {
		return (T) this.repository.findOne(id);
	}

	@Transactional(readOnly = false)
	public void save(T entity) {
		this.repository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(ID id) {
		this.repository.delete(id);
	}

	@Transactional(readOnly = false)
	public void batchDelete(List<ID> ids) {
		for (ID id : ids) {
			this.repository.delete(id);
		}
	}

	public Iterable<T> findAll() {
		return this.repository.findAll();
	}

	public Page<T> findAll(Pageable pageable) {
		return this.repository.findAll(pageable);
	}

	public Page<T> findAll(Predicate predicate, Pageable pageable) {
		return this.repository.findAll(predicate, pageable);
	}

}
