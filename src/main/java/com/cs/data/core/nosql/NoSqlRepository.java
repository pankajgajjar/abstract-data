package com.cs.data.core.nosql;

import java.util.Collection;
import java.util.List;

import com.cs.data.core.GenericDomain;

public interface NoSqlRepository extends NoSqlOperations {

	/*
	 * Saves given object to configured mongoDb database.
	 * 
	 * @see
	 * com.cs.data.core.nosql.NoSqlOperations#save(com.cs.data.core.GenericDomain
	 * )
	 */
	public abstract String save(GenericDomain objectToInsert);

	/*
	 * Updates given object when configured mongoDb database.
	 * 
	 * @see com.cs.data.core.nosql.NoSqlOperations#update(java.lang.Object)
	 */
	public abstract <T> T update(T query);

	/*
	 * Deletes given object when configured mongoDb database.
	 * 
	 * @see com.cs.data.core.nosql.NoSqlOperations#delete(java.lang.Object)
	 */
	public abstract <T> T delete(T objectToDelete);

	/*
	 * Gets object by given object key.
	 * 
	 * @see
	 * com.cs.data.core.nosql.NoSqlOperations#getObjectByKey(com.cs.data.core
	 * .GenericDomain, java.lang.Class)
	 */
	public abstract <P> P getObjectByKey(GenericDomain key, Class<P> type);

	/*
	 * gets object by given hash key and object key by type.
	 * 
	 * @see
	 * com.cs.data.core.nosql.NoSqlOperations#getObjectByKey(java.lang.String,
	 * java.lang.String, java.lang.Class)
	 */
	public abstract <P> P getObjectByKey(String key, String objectKey,
			Class<P> class1);

	/**
	 * Update by id.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param <P>
	 *            the generic type
	 * @param id
	 *            the id
	 * @param field
	 *            the field
	 * @param valueToAdd
	 *            the value to add
	 * @param type
	 *            the type
	 */
	public abstract <T, P> void updateById(String id, String field,
			P valueToAdd, Class<T> type);

	/*
	 * Gets all documents of given collection.
	 * 
	 * @see com.cs.data.core.nosql.NoSqlOperations#findAll(java.lang.Class)
	 */
	public abstract <T> List<T> findAll(Class<T> class1);

	/**
	 * Find.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param key
	 *            the key
	 * @param class1
	 *            the class1
	 * @return the t
	 */
	public abstract <T> T find(String key, Class<T> class1);

	/**
	 * Gets the objects by type.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param field
	 *            the field
	 * @param value
	 *            the value
	 * @param type
	 *            the type
	 * @return the objects by
	 */
	public abstract <T> List<T> getObjectsBy(String field, String value,
			Class<T> type);

	/**
	 * Gets the object for and criteria.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param <P>
	 *            the generic type
	 * @param <Q>
	 *            the generic type
	 * @param secondField
	 *            the second field
	 * @param secondFieldValue
	 *            the second field value
	 * @param firstField
	 *            the first field
	 * @param firstFieldValue
	 *            the first field value
	 * @param type
	 *            the type
	 * @return the object for and criteria
	 */
	public abstract <T, P, Q> List<Q> getObjectForAndCriteria(
			String secondField, P secondFieldValue, String firstField,
			Collection<T> firstFieldValue, Class<Q> type);

	/**
	 * Gets the object by key.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param id
	 *            the id
	 * @param type
	 *            the type
	 * @return the object by key
	 */
	public abstract <T> T getObjectByKey(String id, Class<T> type);

}