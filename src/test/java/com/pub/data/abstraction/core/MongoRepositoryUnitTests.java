package com.pub.data.abstraction.core;


import junit.framework.Assert;
import org.hamcrest.core.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class MongoRepositoryUnitTests {
	
	private PubCrudRepository crudRepository;
	private MongoOperations mongoTemplate;
	private ConfigurableApplicationContext context;
	
	@Before
	public void setUp()
	{
		context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
	    mongoTemplate= (MongoOperations)context.getBean("mongoTemplate");
		crudRepository=new MongoRepository(mongoTemplate);
	}

	@Test
	public void itShouldInsertAnObjectToMongoDB() {
		
		//given
		Student amar=new Student("1", "Amar", "First");
		//when
		crudRepository.insert(amar);
		Student id=mongoTemplate.findById("1", Student.class);
		
		//then
		Assert.assertNotNull(id);
		Assert.assertEquals(amar.getId(), id.getId());
	}

}
