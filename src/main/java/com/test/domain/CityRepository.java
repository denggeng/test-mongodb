package com.test.domain;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.test.framework.repository.BaseRepository;

public interface CityRepository extends BaseRepository<City, BigInteger>, QueryDslPredicateExecutor<City> {


	City findByNameAndStateAllIgnoringCase(String name, String state);

	List<City> findByNameAllIgnoringCase(String name);

}