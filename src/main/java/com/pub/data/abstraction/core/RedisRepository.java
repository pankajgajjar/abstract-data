package com.pub.data.abstraction.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;

public class RedisRepository implements PubCrudRepository {

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisOperations redisTemplate;
	
	public RedisRepository(){
		
	}
	public RedisRepository(@SuppressWarnings("rawtypes") RedisOperations redisTemplate){
		this.redisTemplate=redisTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> String insert(T objectToInsert) {
		redisTemplate.opsForHash().put(objectToInsert.hashCode(),
				objectToInsert.hashCode(), objectToInsert);

		return "inserted";
		// TODO Auto-generated method stub

	}

	@Override
	public <T> T select(T query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T delete(T objectToDelete) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E, P> P getObjectByKey(E key, Class<P> type) {
		// TODO Auto-generated method stub
		return null;
	}

}
