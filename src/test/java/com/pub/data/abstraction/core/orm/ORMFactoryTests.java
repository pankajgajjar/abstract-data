package com.pub.data.abstraction.core.orm;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pub.data.abstraction.core.orm.entities.Student;
import com.pub.data.abstraction.core.orm.repository.StudentRepository;

public class ORMFactoryTests {

	private ORMFactory ormFactory;

	@Before
	public void setUp() {

	}

	@Test
	public void itShouldGetEntityManagerFactoryForHibernate() {

		//Given
		EntityManager entityManager;
		//when
		entityManager =ORMFactory.getEntityManagerForPersistence("hibernate");
		//then
		Assert.assertNotNull(entityManager);
	}
	
	@Test
	public void itShouldSaveStudentObjectToDataBaseWithHibernate(){
		//given
		Student expectedStudent=new Student("1","Amar","Potghan");
		Student actualStudent;
		
		//when
		EntityManager entityManager=ORMFactory.getEntityManagerForPersistence("hibernate");
		entityManager.persist(expectedStudent);
		actualStudent=entityManager.find(Student.class,  expectedStudent.getId());
		
		//then
		
		Assert.assertEquals(expectedStudent.getId(), actualStudent.getId());
		System.out.println(expectedStudent.getId());
		
	}
	
	@Test
	public void itShouldSaveStudentObjectToDataBaseWithEclipseLink(){
		//given
		Student expectedStudent=new Student("2","Esha","Narang");
		Student actualStudent;
		
		//when
		EntityManager entityManager=ORMFactory.getEntityManagerForPersistence("eclipselink");
		entityManager.persist(expectedStudent);
		actualStudent=entityManager.find(Student.class,  expectedStudent.getId());
		
		//then
		
		Assert.assertEquals(expectedStudent.getId(), actualStudent.getId());
		System.out.println(expectedStudent.getId());
		
	}

}
