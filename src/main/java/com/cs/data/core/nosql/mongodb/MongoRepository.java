package com.cs.data.core.nosql.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.cs.data.core.IRepository;

public class MongoRepository implements IRepository {

	public MongoOperations mongoTemplate;

	@Autowired
	public MongoRepository(MongoOperations mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public <T> String insert(T objectToInsert) {
		mongoTemplate.insert(objectToInsert);

		return "inserted";

	}

	@Override
	public <T> T update(T query) {

		return null;
	}

	@Override
	public <T> T delete(T objectToDelete) {
		return null;
	}

	@Override
	public <E, P> P getObjectByKey(E key, Class<P> type) {
		return mongoTemplate.findById(key, type);
	}

}
