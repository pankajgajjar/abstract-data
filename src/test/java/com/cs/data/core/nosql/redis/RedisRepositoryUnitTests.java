package com.cs.data.core.nosql.redis;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cs.data.core.jpa.entities.Student;
import com.cs.data.core.nosql.NoSqlOperations;
import com.cs.data.core.nosql.redis.RedisRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class RedisRepositoryUnitTests {

	private NoSqlOperations crudRepository;

	@Autowired
	private RedisOperations<?, ?> redisTemplate;
	private ConfigurableApplicationContext context;

	@Before
	public void setUp() throws Exception {

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

		System.out.println(actualStudent);

		// then
		Assert.assertEquals(amar.getId(), actualStudent.getId());
	}

}
