package com.cs.data.core;



/**
 * The Interface IRepository.
 */
public interface IRepository {
	
	 /**
 	 * Save.
 	 *
 	 * @param objectToInsert the object to insert
 	 * @return the string
 	 */
 	String save(GenericDomain objectToInsert);
	
	/**
	 * Update.
	 *
	 * @param <T> the generic type
	 * @param query the query
	 * @return the t
	 */
	<T> T update(T query);
	
	/**
	 * Delete.
	 *
	 * @param <T> the generic type
	 * @param objectToDelete the object to delete
	 * @return the t
	 */
	<T> T delete(T objectToDelete);
	
	/**
	 * Gets the object by key.
	 *
	 * @param <P> the generic type
	 * @param key the key
	 * @param s the s
	 * @return the object by key
	 */
	<P> P getObjectByKey(GenericDomain key, Class<P> s);
	
}
