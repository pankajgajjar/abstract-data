package com.cs.data.core.nosql;


public interface NoSqlOperations {
	
	public <T> T findAll();
	public <T> String insert(T objectToInsert);
	public <E,P> P findOne(E key, Class<P> type);

}
