package com.pub.data.abstraction.core;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

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
		Student amar=new Student("2", "Amar", "First");
		//when
		crudRepository.insert(amar);
		Student id=mongoTemplate.findById("2", Student.class);
		
		//then
		Assert.assertNotNull(id);
		Assert.assertEquals(amar.getId(), id.getId());
	}
	
	@Test 
	public void itShouldGetObjectByKey()
	{
		//given 
		String id="1";
		Class<Student> type=Student.class;
		Student expectedAmar=new Student("1", "Amar", "First");
		//when
		
		Student actualAmar= crudRepository.getObjectByKey(id,type);
		//then
		Assert.assertEquals(expectedAmar.getId(), actualAmar.getId());
		
	}

}
