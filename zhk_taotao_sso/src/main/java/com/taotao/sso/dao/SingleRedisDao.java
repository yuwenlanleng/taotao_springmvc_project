package com.taotao.sso.dao;

public interface SingleRedisDao {
	void setValue(String key, String value);

	String getValue(String key);

	Long incr(String key);

	Long decr(String key);

	long hset(String index, String categoryId, String nodes);

	String hget(String index,long categoryId);

	long expire(String key, int seconds);
}
