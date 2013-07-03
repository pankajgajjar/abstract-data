package com.pub.data.abstraction.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoRepository implements PubCrudRepository{
	
	
	@Autowired
	public MongoOperations mongoTemplate;
	
	public MongoRepository()
	{
		
	}
	
	public MongoRepository(MongoOperations mongoTemplate)
	{
		this.mongoTemplate=mongoTemplate;
	}
	
	@Override
	public <T> String insert(T objectToInsert) {
		mongoTemplate.insert(objectToInsert);
		
		return "inserted";
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T select(T query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T delete(T objectToDelete) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
