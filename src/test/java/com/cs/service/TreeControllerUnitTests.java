package com.cs.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.cs.service.ContentTree;

import static org.fest.assertions.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class TreeControllerUnitTests {
	
	private ContentTree treeController;
	@Before
	public void setUp(){
		treeController=new ContentTree();
	}
	
	@Test
	public void itShouldReturnIndexPage(){
		//given
		String expectedPage="redirect:/pages/index.html";
		
		//when
		String actualPage=treeController.getIndexPage();
		
		//then
		
		assertThat(actualPage).isEqualTo(expectedPage);
		
	}

}
