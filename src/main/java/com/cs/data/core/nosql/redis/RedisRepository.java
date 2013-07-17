package com.cs.data.core.nosql.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;

import com.cs.data.core.IRepository;

public class RedisRepository implements IRepository {
	
	@SuppressWarnings("rawtypes")
	private RedisOperations redisTemplate;
	
	public RedisRepository(){
		
	}
	
	@Autowired
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
	public <E, P> P getObjectByKey(E key, Class<P> type) {
		// TODO Auto-generated method stub
		
		P object=(P)redisTemplate.opsForHash().get(key.hashCode(), key.hashCode());
		return object;
	}

}
