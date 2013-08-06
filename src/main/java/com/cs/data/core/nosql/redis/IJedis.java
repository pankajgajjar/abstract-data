package com.cs.data.core.nosql.redis;

import redis.clients.jedis.Jedis;

public interface IJedis {

	public String get(String key);
	public String set(String key, String value);
}
