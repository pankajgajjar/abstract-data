package com.cs.data.core;

public interface IRepository {
	
	 String insert(GenericDomain objectToInsert);
	<T> T update(T query);
	<T> T delete(T objectToDelete);
	<P> P getObjectByKey(GenericDomain key, Class<P> type);
}
