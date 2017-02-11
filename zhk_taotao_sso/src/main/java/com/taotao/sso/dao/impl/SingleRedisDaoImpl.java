package com.taotao.sso.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.taotao.sso.dao.SingleRedisDao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class SingleRedisDaoImpl implements SingleRedisDao {

	// 单机
	@Autowired
	private JedisPool jedisPool;

	@Override
	public void setValue(String key, String value) {
		// TODO Auto-generated method stub
		Jedis resource = jedisPool.getResource();
		resource.set(key, value);
		resource.close();
	}

	@Override
	public String getValue(String key) {
		// TODO Auto-generated method stub
		Jedis resource = jedisPool.getResource();
		String resultContent = resource.get(key);
		resource.close();
		return resultContent;
	}

	@Override
	public Long incr(String key) {
		// TODO Auto-generated method stub
		Jedis resource = jedisPool.getResource();
		long result = resource.incr(key);
		resource.close();
		return result;
	}

	@Override
	public Long decr(String key) {
		// TODO Auto-generated method stub
		Jedis resource = jedisPool.getResource();
		long result = resource.decr(key);
		resource.close();
		return result;
	}

	@Override
	public long hset(String index,String categoryId, String nodes) {
		// TODO Auto-generated method stub
		Jedis resource = jedisPool.getResource();
		Long hset = resource.hset(index, String.valueOf(categoryId), nodes);
		resource.close();
		return hset;
	}

	@Override
	public String hget(String index ,long categoryId) {
		// TODO Auto-generated method stub
		Jedis resource = jedisPool.getResource();
		String hgetResult = resource.hget(index, String.valueOf(categoryId));
		resource.close();
		return hgetResult;
	}

	@Override
	public long expire(String key, int seconds) {
		// TODO Auto-generated method stub
		Jedis resource = jedisPool.getResource();
		long expireResult = resource.expire(key, seconds);
		resource.close();
		return expireResult;
	}

}
