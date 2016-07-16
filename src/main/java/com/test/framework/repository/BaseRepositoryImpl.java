package com.test.framework.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.QueryDslMongoRepository;

public class BaseRepositoryImpl<T, ID extends Serializable> extends QueryDslMongoRepository<T, ID>
		implements BaseRepository<T, ID> {

	private final MongoOperations mongoOperations;
	private final MongoEntityInformation<T, ID> entityInformation;

	public BaseRepositoryImpl(MongoEntityInformation<T, ID> entityInformation, MongoOperations mongoOperations) {
		super(entityInformation, mongoOperations);

		// Keep the EntityManager around to used from the newly introduced
		// methods.
		this.mongoOperations = mongoOperations;
		this.entityInformation = entityInformation;
	}

	public void sharedCustomMethod(ID id) {
		// implementation goes here
	}

	public MongoOperations getMongoOperations() {
		return mongoOperations;
	}

	public MongoEntityInformation<T, ID> getEntityInformation() {
		return entityInformation;
	}
}