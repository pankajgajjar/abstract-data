package com.cs.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.data.core.nosql.redis.RedisRepository;


/**
 * The Class ViewStructureCache.
 */
@Component
public class ViewStructureCache {

	/** The no sql template for redis. */
	private RedisRepository noSqlTemplateForRedis;
	
	/** The key. */
	private final String KEY = "view";

	/**
	 * Instantiates a new view structure cache.
	 *
	 * @param noSqlTemplateForRedis the no sql template for redis
	 */
	@Autowired
	public ViewStructureCache(RedisRepository noSqlTemplateForRedis) {
		this.noSqlTemplateForRedis = noSqlTemplateForRedis;

	}

	/**
	 * Gets the current view structure.
	 *
	 * @return the current view structure
	 */
	public String getCurrentViewStructure() {

		return noSqlTemplateForRedis.get(KEY);
	}

	/**
	 * Sets the current view structure.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void setCurrentViewStructure(String key, String value) {
		// TODO Auto-generated method stub
		noSqlTemplateForRedis.set(key, value);
	}

}
