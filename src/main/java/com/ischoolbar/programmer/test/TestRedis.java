package com.ischoolbar.programmer.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class TestRedis {
	/*@Test*/
	public void method(){
		
	}
	
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("config/redis-context.xml");
		RedisTemplate jedis=(RedisTemplate) context.getBean("redisTemplate");	
		jedis.opsForValue().set("changex","����");
		System.out.println(jedis.opsForValue().get("changex"));
	}
}
