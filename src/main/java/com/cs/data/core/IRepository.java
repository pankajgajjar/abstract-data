package com.cs.data.core;

public interface IRepository {
	
	<T> String insert(T objectToInsert);
	<T> T update(T query);
	<T> T delete(T objectToDelete);
	<E,P> P getObjectByKey(E key, Class<P> type);
}
