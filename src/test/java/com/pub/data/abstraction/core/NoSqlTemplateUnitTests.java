package com.pub.data.abstraction.core;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class NoSqlTemplateUnitTests {
	
	@Autowired
	private NoSqlOperations noSqlTemplate;
	
	
	
	@Before
	public void setUp()
	{
		/*context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
	    mongoTemplate= (MongoOperations)context.getBean("noSqlTemp");
		crudRepository=new MongoRepository(mongoTemplate);*/
	}

	@Test
	public void itShouldInsertObjectToMongoDB() {
		
		//given
		
		Student amar=new Student("8", "Amar", "First");
		
		//when
		
		String id=noSqlTemplate.insert(amar);
		
		//then

		Assert.assertNotNull(id);
		
		
		
		
	}
	
	@Test 
	public void itShouldInsertObjectToRedis(){
		//given
		
				Student amar=new Student("8", "Amar", "First");
				
				//when
				
				String id=noSqlTemplate.insert(amar);
				
				//then

				Assert.assertNotNull(id);
		
	}

}
