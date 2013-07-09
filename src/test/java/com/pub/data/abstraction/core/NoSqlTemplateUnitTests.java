package com.pub.data.abstraction.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pub.data.abstraction.core.orm.entities.Student;

//@RunWith(SpringJUnit4ClassRunner.class)
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

		Student amar = new Student("8", "Amar", "First");

		// when

		String id = noSqlTemplateForMongo.insert(amar);

		// then

		Assert.assertNotNull(id);

	}

	@Test
	public void itShouldInsertObjectToRedis() {

		// given

		Student amar = new Student("8", "Amar", "First");

		// when

		String id = noSqlTemplateForRedis.insert(amar);

		// then

		Assert.assertNotNull(id);

	}

	@Test
	public void itShouldGetAnObjectFromMongoDB() {

		// given
		Student student = new Student("8", "Amar", "First");
		String id = "8";
		// when
		Student actualStudent = noSqlTemplateForMongo
				.findOne(id, Student.class);
		// then
		Assert.assertEquals(student.getName(), actualStudent.getName());

	}

	@Test
	public void itShouldGetAnObjectFromRedis() {
		// given
		Student student = new Student("100", "Esha", "First");
		noSqlTemplateForRedis.insert(student);
		// when
		Student actualStudent = noSqlTemplateForRedis.findOne(student,
				Student.class);
		// then
		System.out.println(actualStudent.getName());
		Assert.assertEquals(student.getName(), actualStudent.getName());

	}

}
