package com.cs.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.persistence.jpa.jpql.parser.WhenClause;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.utils.FileUtils;
import static org.mockito.Mockito.*;

import static org.fest.assertions.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
public class DimensionServiceUnitTests {

	private Dimension dimension;
	@Mock
	private FileUtils fileUtils;

	@Test
	public void itShouldGetAllAvailableDimensions() throws IOException,
			URISyntaxException {
		// given
		dimension = new Dimension(fileUtils);
		String content = "expected";
		when(fileUtils.getFileContents("dimensions.json")).thenReturn(content);
		// when

		String actualContent=dimension.getAllAvailable();
		// then
		verify(fileUtils).getFileContents("dimensions.json");
		assertThat(actualContent).isEqualTo(content);
	}

}
