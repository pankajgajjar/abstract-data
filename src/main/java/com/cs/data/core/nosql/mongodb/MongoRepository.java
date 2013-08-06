package com.cs.data.core.nosql.mongodb;

import java.util.List;

import org.mockito.internal.matchers.Find;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.cs.data.core.GenericDomain;
import com.cs.data.core.nosql.NoSqlOperations;

@Component
public class MongoRepository implements NoSqlOperations {

	public MongoOperations mongoTemplate;

	@Autowired
	public MongoRepository(MongoOperations mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public String save(GenericDomain objectToInsert) {
		mongoTemplate.save(objectToInsert);

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
	public <P> P getObjectByKey(GenericDomain key, Class<P> type) {
		return mongoTemplate.findById(key.getKey(), type);
	}

	@Override
	public <P> P getObjectByKey(String key, String objectKey, Class<P> class1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findAll(Class<T> class1) {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(class1);
	}

	public <T> T find(String key, Class<T> class1) {

		return mongoTemplate.findById(key, class1);

	}
}
