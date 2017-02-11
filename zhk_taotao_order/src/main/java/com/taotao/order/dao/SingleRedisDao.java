package com.taotao.order.dao;

public interface SingleRedisDao {
	void setValue(String key ,String value);
	String getValue(String key);
	Long incr(String key);
	Long decr(String key);
	long hset(long categoryId,String nodes);
	String hget(long categoryId);
}
