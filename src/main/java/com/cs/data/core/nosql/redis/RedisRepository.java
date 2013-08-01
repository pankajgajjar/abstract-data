package com.cs.data.core.nosql.redis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Component;

import com.cs.data.core.GenericDomain;
import com.cs.data.core.nosql.NoSqlOperations;
import com.cs.model.Tree;

@Component
public class RedisRepository implements NoSqlOperations {

	@SuppressWarnings("rawtypes")
	private RedisOperations redisTemplate;

	public RedisRepository() {

	}

	@Autowired
	public RedisRepository(
			@SuppressWarnings("rawtypes") RedisOperations redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String insert(GenericDomain objectToInsert) {
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

	@SuppressWarnings("unchecked")
	@Override
	public <P> P getObjectByKey(GenericDomain key, Class<P> type) {
		// TODO Auto-generated method stub

		P object = (P) redisTemplate.opsForHash().get(key.getKey(),
				key.getObjectKey());

		return object;
	}
	
	public <P> P getObjectByKey(String key, String objectkey,Class<P> type){
		P object = (P) redisTemplate.opsForHash().get(key,
				objectkey);
		return object;
		
	}

	@Override
	public <T> List<T> findAll(Class<T> class1) {
		// TODO Auto-generated method stub
		return null;
	}

}
