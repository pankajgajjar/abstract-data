package com.cs.factory;

import org.springframework.beans.factory.annotation.Autowired;

import com.cs.data.core.nosql.redis.RedisRepository;
import com.cs.model.DimensionID;

public class IDFactory {
	static RedisRepository redisRepository;
	
	@Autowired
	public IDFactory(RedisRepository redisRepository){
		
		this.redisRepository=redisRepository;
		
	}

	public static int getObjectId(String type) {
		DimensionID id = new DimensionID("counter");

		DimensionID counter = redisRepository.getObjectByKey(id.getId(),
				id.getObjectKey(), DimensionID.class);
		int cntr = counter.getCounter();
		cntr++;
		id.setCounter(cntr);
		redisRepository.insert(id);

		return cntr;

	}
}
