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

@Component
public class RedisRepository implements NoSqlOperations {

	@SuppressWarnings("rawtypes")
	private RedisOperations redisTemplate;

	@Autowired
	public RedisRepository(RedisOperations redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String save(GenericDomain objectToInsert) {
		redisTemplate.opsForHash().put(objectToInsert.getKey(),
				objectToInsert.getObjectKey(), objectToInsert);

		return "inserted";
		// TODO Auto-generated method stub

	}

	@Override
	public <T> T update(T query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T delete(T objectToDelete) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(String key) {
		// TODO Auto-generated method stub
		redisTemplate.delete(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <P> P getObjectByKey(GenericDomain key, Class<P> type) {
		// TODO Auto-generated method stub

		P object = (P) redisTemplate.opsForHash().get(key.getKey(),
				key.getObjectKey());

		return object;
	}

	public <P> P getObjectByKey(String key, String objectkey, Class<P> type) {
		P object = (P) redisTemplate.opsForHash().get(key, objectkey);
		return object;

	}

	@Override
	public <T> List<T> findAll(Class<T> class1) {
		// TODO Auto-generated method stub
		return null;
	}

	public String get(String key) {
		// TODO Auto-generated method stub
		return (String) redisTemplate.opsForValue().get(key);
	}

	public void set(String key, String value) {
		// TODO Auto-generated method stub
		redisTemplate.opsForValue().set(key, value);
	}

	public Set findAllKeys(String pattern) {
		// TODO Auto-generated method stub
		return redisTemplate.keys(pattern);

	}

	public List<String> findAllValues(String keyPattern) {

		return redisTemplate.opsForValue().multiGet(findAllKeys(keyPattern));

	}

}
