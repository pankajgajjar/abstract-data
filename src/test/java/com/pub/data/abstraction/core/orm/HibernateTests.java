package com.pub.data.abstraction.core.orm;


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pub.data.abstraction.core.orm.entities.Student;
import com.pub.data.abstraction.core.orm.repository.StudentRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context-jpa-test.xml")
public class HibernateTests {

	@Autowired
	StudentRepository repository;
	
	@Test
	public void test() {
		Student post = new Student("1","Amar","First");
		
		repository.save(post);
		
		Student dbstudent = repository.findOne(post.getId());
		assertNotNull(dbstudent);
		System.out.println(dbstudent.getName());
	}

}
