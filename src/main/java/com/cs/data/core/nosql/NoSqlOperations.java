package com.cs.data.core.nosql;

import com.cs.data.core.GenericDomain;
import com.cs.data.core.IRepository;
import com.cs.model.Tree;


public interface NoSqlOperations extends IRepository {
	
	String insert(GenericDomain objectToInsert);
	<T> T update(T query);
	<T> T delete(T objectToDelete);
	<P> P getObjectByKey(GenericDomain key, Class<P> type);
	<P> P getObjectByKey(String key, String objectKey, Class<P> class1);

}
