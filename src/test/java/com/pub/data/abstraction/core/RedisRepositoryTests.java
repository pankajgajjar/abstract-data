package com.pub.data.abstraction.core;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


public class RedisRepositoryTests {
	
	
	private PubCrudRepository crudRepository;
	private RedisOperations redisTemplate;
	private ConfigurableApplicationContext context;

	@Before
	public void setUp() throws Exception {
		 
		context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
	    redisTemplate= (RedisOperations)context.getBean("redisTemplate");
	    crudRepository=new RedisRepository(redisTemplate);
	}

	@Test
	public void itShouldInsertAnObjectToRedis() {
		
		//given
		Student amar=new Student("1", "Amar", "First");
		
		//When
		
		String id=crudRepository.insert(amar);
		
		//then
		Assert.assertNotNull(id);
		
		
	}
	
	@Test
	public void itShouldReturnAnObjectByKey()
	{
		//given
		Student amar=new Student("2", "Esha", "First");
		crudRepository.insert(amar);
		//when
		Student actualStudent=crudRepository.getObjectByKey(amar, Student.class);
		//then
		Assert.assertEquals(amar.getId(), actualStudent.getId());
	}

}
