package com.cs.data.core.nosql;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cs.data.core.jpa.entities.Student;
import com.cs.data.core.nosql.NoSqlOperations;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class NoSqlTemplateUnitTests {

	@Autowired
	private NoSqlOperations noSqlTemplateForMongo;

	@Autowired
	private NoSqlOperations noSqlTemplateForRedis;

	@Before
	public void setUp() {

	}

	@Test
	public void itShouldInsertObjectToMongoDB() {

		// given

		Student studentInserted = new Student("8", "Amar", "First");

		// when

		String id = noSqlTemplateForMongo.save(studentInserted);
		Student studentRetrieved = noSqlTemplateForMongo.getObjectByKey(studentInserted, Student.class);

		// then

		Assert.assertNotNull(id);
		Assert.assertEquals(studentInserted.getId(), studentRetrieved.getId());
		Assert.assertEquals(studentInserted.getClass(),
				studentRetrieved.getClass());

	}

	@Test
	public void itShouldInsertObjectToRedis() {

		// given

		Student studentInserted = new Student("9", "Katrina", "First");

		// when

		String id = noSqlTemplateForRedis.save(studentInserted);
		Student studentRetrieved = noSqlTemplateForRedis.getObjectByKey(
				studentInserted, Student.class);
		// then

		Assert.assertNotNull(id);
		Assert.assertEquals(studentInserted.getId(), studentRetrieved.getId());
		Assert.assertEquals(studentInserted.getClass(),
				studentRetrieved.getClass());

	}

	@Test
	public void itShouldGetAnObjectFromMongoDB() {

		// given
		Student student = new Student("8", "Amar", "First");
		// when
		Student actualStudent = noSqlTemplateForMongo.getObjectByKey(student,
				Student.class);
		// then
		Assert.assertEquals(student.getName(), actualStudent.getName());

	}

	@Test
	public void itShouldGetAnObjectFromRedis() {
		// given
		Student student = new Student("9", "Katrina", "First");
		noSqlTemplateForRedis.save(student);
		// when
		Student actualStudent = noSqlTemplateForRedis.getObjectByKey(student,
				Student.class);
		// then
		Assert.assertEquals(student.getName(), actualStudent.getName());

	}

}
