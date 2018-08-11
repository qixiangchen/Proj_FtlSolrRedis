package com.test.util;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisAccessor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class RedisTool {
	//注入Redis模板对象
	@Autowired
	//@Qualifier("redisTemplate") 
	private RedisTemplate redis;

	//保存对象类型到Redis内存服务器
	public void saveObject(String key,Object obj)
	{
		redis.opsForValue().set(key,obj);
	}

	//根据Key获取Redis内存对象
	public Object getObject(String key)
	{
		return redis.opsForValue().get(key);
	}

	//保存Map对象到Redis内存服务器
	public void saveMap(String key,Map map)
	{
		redis.opsForHash().putAll(key,map);
	}

	//根据Key获取Redis Map对象
	public Map getMap(String key)
	{
		return redis.opsForHash().entries(key);
	}

	//保持List对象到Redis内存服务器
	public void saveList(String key,List list)
	{
		redis.opsForList().leftPushAll(key, list);
	}

	//根据Key获取Redis List对象
	public List getList(String key)
	{
		return redis.opsForList().range(key, 0, -1);
	}
}
