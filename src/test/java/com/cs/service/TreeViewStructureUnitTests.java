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
public class TreeViewStructureUnitTests {

	private TreeViewStructure structure;
	@Mock
	private FileUtils fileUtils;

	@Test
	public void itShouldGetAllAvailableDimensions() throws IOException,
			URISyntaxException {
		// given
		String viewStructureId = "1";
		structure = new TreeViewStructure(fileUtils);
		String content = "expected";
		when(fileUtils.getFileContents("schema1.json")).thenReturn(content);
		// when

		String actualContent = structure.get(viewStructureId);
		// then
		verify(fileUtils).getFileContents("schema1.json");
		assertThat(actualContent).isEqualTo(content);
	}

	@Test
	public void itShouldGetDefaultViewStructure() throws IOException,
			URISyntaxException {
		// given
		structure = new TreeViewStructure(fileUtils);
		String content = "expected";
		when(fileUtils.getFileContents("schema1.json")).thenReturn(content);
		// when

		String actualContent = structure.getDefault();
		// then
		verify(fileUtils).getFileContents("schema1.json");
		assertThat(actualContent).isEqualTo(content);
	}

}
