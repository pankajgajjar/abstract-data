package com.pub.data.abstraction.core;


public interface NoSqlOperations {
	
	public <T> T findAll();
	public <T> T findOne();
	public <T> String insert(T objectToInsert);

}
