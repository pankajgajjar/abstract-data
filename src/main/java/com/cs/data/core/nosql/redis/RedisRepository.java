package com.cs.data.core.nosql.redis;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.cs.data.core.GenericDomain;
import com.cs.data.core.nosql.NoSqlOperations;
import com.cs.model.Tree;

/**
 * The Class RedisRepository.
 */
@Component
public class RedisRepository implements NoSqlOperations {

	/** The redis template. */
	@SuppressWarnings("rawtypes")
	private RedisOperations redisTemplate;

	/**
	 * Instantiates a new redis repository.
	 *
	 * @param redisTemplate the redis template
	 */
	@Autowired
	public RedisRepository(RedisOperations redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/* Saves given object to configured redis Store.
	 * @see com.cs.data.core.nosql.NoSqlOperations#save(com.cs.data.core.GenericDomain)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String save(GenericDomain objectToInsert) {
		redisTemplate.opsForHash().put(objectToInsert.getKey(),
				objectToInsert.getObjectKey(), objectToInsert);

		return "inserted";
		// TODO Auto-generated method stub

	}

	/* Updates given object when configured redis Store.
	 * @see com.cs.data.core.nosql.NoSqlOperations#update(java.lang.Object)
	 */
	@Override
	public <T> T update(T query) {
		// TODO Auto-generated method stub
		return null;
	}

	/* Deletes object when configured redis Store.
	 * @see com.cs.data.core.nosql.NoSqlOperations#delete(java.lang.Object)
	 */
	@Override
	public <T> T delete(T objectToDelete) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Delete.
	 *
	 * @param key the key
	 */
	public void delete(String key) {
		// TODO Auto-generated method stub
		redisTemplate.delete(key);
	}

	/* Gets object by given object key.
	 * @see com.cs.data.core.nosql.NoSqlOperations#getObjectByKey(com.cs.data.core.GenericDomain, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <P> P getObjectByKey(GenericDomain key, Class<P> type) {
		// TODO Auto-generated method stub

		P object = (P) redisTemplate.opsForHash().get(key.getKey(),
				key.getObjectKey());

		return object;
	}

	/* Gets object by string key and object key by type.
	 * @see com.cs.data.core.nosql.NoSqlOperations#getObjectByKey(java.lang.String, java.lang.String, java.lang.Class)
	 */
	public <P> P getObjectByKey(String key, String objectkey, Class<P> type) {
		P object = (P) redisTemplate.opsForHash().get(key, objectkey);
		return object;

	}

	/* Gets all objects.
	 * @see com.cs.data.core.nosql.NoSqlOperations#findAll(java.lang.Class)
	 */
	@Override
	public <T> List<T> findAll(Class<T> class1) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the value for given key.
	 *
	 * @param key the key
	 * @return the string
	 */
	public String get(String key) {
		// TODO Auto-generated method stub
		return (String) redisTemplate.opsForValue().get(key);
	}

	/**
	 * Sets the value against given key.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void set(String key, String value) {
		// TODO Auto-generated method stub
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * Find all keys.
	 *
	 * @param pattern the pattern
	 * @return the sets the
	 */
	public Set findAllKeys(String pattern) {
		// TODO Auto-generated method stub
		return redisTemplate.keys(pattern);

	}

	/**
	 * Find all values.
	 *
	 * @param keyPattern the key pattern
	 * @return the list
	 */
	public List<String> findAllValues(String keyPattern) {

		return redisTemplate.opsForValue().multiGet(findAllKeys(keyPattern));

	}

}
