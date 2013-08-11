package com.cs.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.data.core.nosql.redis.RedisRepository;

@Component
public class ViewStructureCache {

	private RedisRepository noSqlTemplateForRedis;
	private final String KEY = "view";

	@Autowired
	public ViewStructureCache(RedisRepository noSqlTemplateForRedis) {
		this.noSqlTemplateForRedis = noSqlTemplateForRedis;

	}

	public String getCurrentViewStructure() {

		return noSqlTemplateForRedis.get(KEY);
	}

	public void setCurrentViewStructure(String key, String value) {
		// TODO Auto-generated method stub
		noSqlTemplateForRedis.set(key, value);
	}

}
