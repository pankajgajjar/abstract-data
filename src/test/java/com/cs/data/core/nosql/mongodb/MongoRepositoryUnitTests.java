package com.cs.data.core.nosql.mongodb;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.cs.data.core.IRepository;
import com.cs.data.core.jpa.entities.Student;
import com.cs.data.core.nosql.NoSqlOperations;
import com.cs.data.core.nosql.mongodb.MongoRepository;

public class MongoRepositoryUnitTests {
	
	private NoSqlOperations crudRepository;
	private MongoOperations mongoTemplate;
	private ConfigurableApplicationContext context;
	
	@Before
	public void setUp()
	{
		context = new ClassPathXmlApplicationContext("classpath:application-context-test.xml");
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
		
		Student actualAmar= crudRepository.getObjectByKey(expectedAmar,type);
		//then
		Assert.assertEquals(expectedAmar.getId(), actualAmar.getId());
		
	}
	
/*	@Test
	public void itShouldUpdateExistingEntity(){
		//given
		String id="1";
		Class<Student> type=Student.class;
		Student studentUpdated=new Student("1","Esha","Second");
		
		//when
		String flag1=crudRepository.insert(studentUpdated);
		Student actualStudent= crudRepository.getObjectByKey(id,type);
		
		//then
		Assert.assertEquals(studentUpdated.getName(), actualStudent.getName());
		Assert.assertNotNull(flag1);
		Assert.assertEquals(studentUpdated.getStandard(), actualStudent.getStandard());
		
	}*/

}
