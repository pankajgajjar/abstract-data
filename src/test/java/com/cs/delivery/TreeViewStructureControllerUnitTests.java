package com.cs.delivery;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.persistence.jpa.jpql.parser.WhenClause;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.delivery.TreeViewStructureController;
import com.cs.utils.FileUtils;
import static org.mockito.Mockito.*;

import static org.fest.assertions.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class TreeViewStructureControllerUnitTests {

	private TreeViewStructureController structure;
	@Mock
	private FileUtils fileUtils;

	@Mock
	private JSONParser parser;

	@Test
	public void itShouldGetAllAvailableDimensions() throws IOException,
			URISyntaxException, ParseException {
		// given
		Object test = new Object();
		String viewStructureId = "1";
		structure = new TreeViewStructureController(fileUtils);
		String content = "[{}]";
		when(fileUtils.getFileContents("schema1.json")).thenReturn(content);
		when(parser.parse(content)).thenReturn(test);
		// when

		
		Object actualObject=structure.get(viewStructureId);;
		// then
		assertThat(actualObject.toString()).isEqualTo(content);
		verify(fileUtils).getFileContents("schema1.json");
	}

	@Test
	public void itShouldGetDefaultViewStructure() throws IOException,
			URISyntaxException {
		// given
		structure = new TreeViewStructureController(fileUtils);
		String content = "expected";
		when(fileUtils.getFileContents("schema1.json")).thenReturn(content);
		// when

		String actualContent = structure.getDefault();
		// then
		verify(fileUtils).getFileContents("schema1.json");
		assertThat(actualContent).isEqualTo(content);
	}

}
