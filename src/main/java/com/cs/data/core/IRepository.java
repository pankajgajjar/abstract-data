package com.cs.data.core;

import com.cs.model.Tree;

public interface IRepository {
	
	 String insert(GenericDomain objectToInsert);
	<T> T update(T query);
	<T> T delete(T objectToDelete);
	<P> P getObjectByKey(GenericDomain key, Class<P> s);
	
}
