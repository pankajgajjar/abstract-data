package com.pub.data.abstraction.core;

import org.springframework.beans.factory.annotation.Autowired;


public class NoSqlTemplate implements NoSqlOperations {
	
	private PubCrudRepository crudRepository;
	
	@Autowired
	public NoSqlTemplate(PubCrudRepository crudRepository){
		
		this.crudRepository=crudRepository;
	}
	
	

	@Override
	public <T> T findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public <T> String insert(T objectToInsert) {
		// TODO Auto-generated method stub
		String id= crudRepository.insert(objectToInsert);
		return id;
	}



	@Override
	public <E, P> P findOne(E key, Class<P> type) {
		// TODO Auto-generated method stub
		P object=crudRepository.getObjectByKey(key, type);
		return object;
	}
	

}
