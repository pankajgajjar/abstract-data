package com.cs.data.core.nosql;

import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.cs.data.core.GenericDomain;
import com.cs.data.core.nosql.redis.RedisRepository;

/**
 * Interface that specifies a basic set InMemory Databse Operations. Implemented
 * by {@link RedisRepository}. A useful option for extensibility and testability
 * (as it can be easily mocked, stubbed, or be the target of a JDK proxy).
 * 
 * @author Amar
 * 
 */
public interface InMemoryNoSqlRepository extends NoSqlOperations {

	/**
	 * Method that saves a domain object to inmemory database.
	 * 
	 * @param : Domain Object
	 * 
	 */

	public abstract String save(GenericDomain objectToInsert);

	/**
	 * Method that fires given query to inMemory Database
	 * 
	 * @param Query
	 *            object
	 * 
	 */
	public abstract <T> T update(T query);

	/**
	 * Method that deletes given object from inMemory Database
	 * 
	 * @param Domain
	 *            Object
	 * 
	 */
	public abstract <T> T delete(T objectToDelete);

	/**
	 * Method that deletes the value of given String key.
	 * 
	 * @param key
	 *            the key
	 */
	public abstract void delete(String key);

	/**
	 * Method that deletes the value of given Domain object as key.
	 * 
	 * @param Domain
	 *            Object as a key, Type of domain object
	 */
	public abstract <P> P getObjectByKey(GenericDomain key, Class<P> type);

	/**
	 * Method that retrieves the value of given String as key, hash key of
	 * object, and type.
	 * 
	 * @param Domain
	 *            Object as a key, Type of domain object
	 */
	public abstract <P> P getObjectByKey(String key, String objectkey,
			Class<P> type);

	/**
	 * Method that retrieves all the values from given database by class of the
	 * domain Object
	 * 
	 * @param Domain
	 *            Object as a key, Type of domain object
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