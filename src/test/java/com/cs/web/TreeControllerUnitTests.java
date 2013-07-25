package com.cs.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.fest.assertions.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class TreeControllerUnitTests {
	
	private TreeController treeController;
	@Before
	public void setUp(){
		treeController=new TreeController();
	}
	
	@Test
	public void itShouldReturnIndexPage(){
		//given
		String expectedPage="index";
		
		//when
		ModelAndView actualPage=treeController.getIndexPage();
		
		//then
		
		assertThat(actualPage.getViewName()).isEqualTo(expectedPage);
		
	}

}
