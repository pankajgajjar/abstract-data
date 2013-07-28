package com.cs.factory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;

import com.cs.data.core.nosql.redis.RedisRepository;
import com.cs.model.DimensionID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class IDFactoryIntegrationTests {
	
	private RedisRepository redisRepository;
	
	@Autowired
	private RedisOperations<?, ?> redisTemplate;
	
	@Test
	public void createNew(){
		
		redisRepository=new RedisRepository(redisTemplate);
		IDFactory factory=new IDFactory(redisRepository);
		DimensionID id=new DimensionID("counter");
		id.setId("counter");
		id.setCounter(101);
		redisRepository.insert(id);
		int expected=IDFactory.getObjectId("object");
		
		Assert.assertEquals(expected, 102);
	}

}
