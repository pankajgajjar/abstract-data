package com.pub.data.abstraction.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoRepository implements PubCrudRepository {

	@Autowired
	public MongoOperations mongoTemplate;

	public MongoRepository() {

	}

	public MongoRepository(MongoOperations mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public <T> String insert(T objectToInsert) {
		mongoTemplate.insert(objectToInsert);

		return "inserted";

	}

	@Override
	public <T> T select(T query) {
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
