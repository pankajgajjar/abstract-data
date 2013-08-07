package com.cs.data.core.nosql.mongodb;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cs.data.core.IRepository;
import com.cs.data.core.jpa.entities.Student;
import com.cs.data.core.jpa.entities.Teacher;
import com.cs.data.core.nosql.NoSqlOperations;
import com.cs.data.core.nosql.mongodb.MongoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class MongoRepositoryUnitTests {

	private MongoRepository crudRepository;
	@Autowired
	private MongoOperations mongoTemplate;

	@Before
	public void setUp() {

		crudRepository = new MongoRepository(mongoTemplate);
	}

	@Test
	public void itShouldInsertAnObjectToMongoDB() {

		// given
		Student amar = new Student("2", "Amar", "First");
		// when
		crudRepository.save(amar);
		Student id = mongoTemplate.findById("2", Student.class);

		// then
		Assert.assertNotNull(id);
		Assert.assertEquals(amar.getId(), id.getId());
	}

	@Test
	public void itShouldGetObjectByKey() {
		// given
		String id = "1";
		Class<Student> type = Student.class;
		Student expectedAmar = new Student("1", "Amar", "First");
		// when

		crudRepository.save(expectedAmar);
		Student actualAmar = crudRepository.getObjectByKey(expectedAmar, type);
		// then
		Assert.assertEquals(expectedAmar.getId(), actualAmar.getId());

	}
@Test
	public void itShouldAppendAListInADocument() {
		// given
		Student amar = new Student("2", "Amar", "First");
		List<Student> students = new ArrayList<Student>();
		Teacher teacher = new Teacher("01", students);

		// when
		crudRepository.save(teacher);
		crudRepository.updateById("01","students",Teacher.class);
		//then
	}
}
