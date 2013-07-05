package com.pub.data.abstraction.core;

public interface PubCrudRepository {
	
	<T> String insert(T objectToInsert);
	<T> T select(T query);
	<T> T delete(T objectToDelete);
	<E,P> P getObjectByKey(E key, Class<P> type);
	

}
