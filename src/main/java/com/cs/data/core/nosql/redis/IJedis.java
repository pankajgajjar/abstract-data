package com.cs.data.core.nosql.redis;

import redis.clients.jedis.Jedis;


/**
 * The Interface IJedis.
 */
public interface IJedis {

	/**
	 * Gets the.
	 *
	 * @param key the key
	 * @return the string
	 */
	public String get(String key);
	
	/**
	 * Sets the.
	 *
	 * @param key the key
	 * @param value the value
	 * @return the string
	 */
	public String set(String key, String value);
}
