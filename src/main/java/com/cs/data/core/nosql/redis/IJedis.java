package com.cs.data.core.nosql.redis;

import redis.clients.jedis.Jedis;

/**
 * Interface to provide pure java drive for Redis. Not implemented yet but a
 * good choice for extensibility.
 */
public interface IJedis {

	/**
	 * Gets the.
	 * 
	 * @param key
	 *            the key
	 * @return the string
	 */
	public String get(String key);

	/**
	 * Sets the.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the string
	 */
	public String set(String key, String value);
}
