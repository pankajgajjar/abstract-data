package com.cs.factory;

import org.junit.Before;
import org.junit.Test;

import com.cs.data.core.GenericDomain;
import com.cs.model.ContentObject;
import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

public class DomainFactoryTests {

	private DomainFactory factory;

	@Before
	public void setUp() {
		factory = new DomainFactory();

	}

	@Test
	public void itShouldCreateInstanceOfContentObject() {
		// when
		GenericDomain testObject = factory.getDomainObject("ContentObject");

		// then
		assertThat(testObject).isInstanceOf(ContentObject.class);

	}
}
