package com.cs.data.core.nosql;

import java.util.List;

import com.cs.data.core.GenericDomain;
import com.cs.data.core.IRepository;
import com.cs.model.DimensionGroup;
import com.cs.model.Tree;



/**
 * The Interface NoSqlOperations.
 */
public interface NoSqlOperations extends IRepository {
	
	/* (non-Javadoc)
	 * @see com.cs.data.core.IRepository#save(com.cs.data.core.GenericDomain)
	 */
	String save(GenericDomain objectToInsert);
	
	/* (non-Javadoc)
	 * @see com.cs.data.core.IRepository#update(java.lang.Object)
	 */
	<T> T update(T query);
	
	/* (non-Javadoc)
	 * @see com.cs.data.core.IRepository#delete(java.lang.Object)
	 */
	<T> T delete(T objectToDelete);
	
	/* (non-Javadoc)
	 * @see com.cs.data.core.IRepository#getObjectByKey(com.cs.data.core.GenericDomain, java.lang.Class)
	 */
	<P> P getObjectByKey(GenericDomain key, Class<P> type);
	
	/**
	 * Gets the object by key.
	 *
	 * @param <P> the generic type
	 * @param key the key
	 * @param objectKey the object key
	 * @param class1 the class1
	 * @return the object by key
	 */
	<P> P getObjectByKey(String key, String objectKey, Class<P> class1);
	
	/**
	 * Find all.
	 *
	 * @param <T> the generic type
	 * @param class1 the class1
	 * @return the list
	 */
	<T> List<T> findAll(Class<T> class1);

}
