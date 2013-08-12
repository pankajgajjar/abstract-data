package com.cs.data.core.nosql;

import java.util.List;
import java.util.Set;

import com.cs.data.core.GenericDomain;

public interface InMemoryNoSqlRepository extends NoSqlOperations {

	/*
	 * Saves given object to configured redis Store.
	 * 
	 * @see
	 * com.cs.data.core.nosql.NoSqlOperations#save(com.cs.data.core.GenericDomain
	 * )
	 */
	public abstract String save(GenericDomain objectToInsert);

	/*
	 * Updates given object when configured redis Store.
	 * 
	 * @see com.cs.data.core.nosql.NoSqlOperations#update(java.lang.Object)
	 */
	public abstract <T> T update(T query);

	/*
	 * Deletes object when configured redis Store.
	 * 
	 * @see com.cs.data.core.nosql.NoSqlOperations#delete(java.lang.Object)
	 */
	public abstract <T> T delete(T objectToDelete);

	/**
	 * Delete.
	 * 
	 * @param key
	 *            the key
	 */
	public abstract void delete(String key);

	/*
	 * Gets object by given object key.
	 * 
	 * @see
	 * com.cs.data.core.nosql.NoSqlOperations#getObjectByKey(com.cs.data.core
	 * .GenericDomain, java.lang.Class)
	 */
	public abstract <P> P getObjectByKey(GenericDomain key, Class<P> type);

	/*
	 * Gets object by string key and object key by type.
	 * 
	 * @see
	 * com.cs.data.core.nosql.NoSqlOperations#getObjectByKey(java.lang.String,
	 * java.lang.String, java.lang.Class)
	 */
	public abstract <P> P getObjectByKey(String key, String objectkey,
			Class<P> type);

	/*
	 * Gets all objects.
	 * 
	 * @see com.cs.data.core.nosql.NoSqlOperations#findAll(java.lang.Class)
	 */
	public abstract <T> List<T> findAll(Class<T> class1);

	/**
	 * Gets the value for given key.
	 * 
	 * @param key
	 *            the key
	 * @return the string
	 */
	public abstract String get(String key);

	/**
	 * Sets the value against given key.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public abstract void set(String key, String value);

	/**
	 * Find all keys.
	 * 
	 * @param pattern
	 *            the pattern
	 * @return the sets the
	 */
	public abstract Set findAllKeys(String pattern);

	/**
	 * Find all values.
	 * 
	 * @param keyPattern
	 *            the key pattern
	 * @return the list
	 */
	public abstract List<String> findAllValues(String keyPattern);

}