package com.cs.data.core.nosql.redis;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisOperations;

import com.cs.data.core.IRepository;
import com.cs.data.core.jpa.entities.Student;
import com.cs.data.core.nosql.redis.RedisRepository;

public class RedisRepositoryUnitTests {

	private IRepository crudRepository;
	private RedisOperations<?, ?> redisTemplate;
	private ConfigurableApplicationContext context;

	@Before
	public void setUp() throws Exception {

		context = new ClassPathXmlApplicationContext(
				"classpath:application-context-test.xml");
		redisTemplate = (RedisOperations<?, ?>) context
				.getBean("redisTemplate");
		crudRepository = new RedisRepository(redisTemplate);
	}

	@Test
	public void itShouldInsertAnObjectToRedis() {

		// given
		Student amar = new Student("1", "Amar", "First");

		// When

		String id = crudRepository.insert(amar);

		// then
		Assert.assertNotNull(id);

	}

	@Test
	public void itShouldReturnAnObjectByKey() {
		// given
		Student amar = new Student("2", "Esha", "First");
		crudRepository.insert(amar);
		// when
		Student actualStudent = crudRepository.getObjectByKey(amar,
				Student.class);
		// then
		Assert.assertEquals(amar.getId(), actualStudent.getId());
	}

}
