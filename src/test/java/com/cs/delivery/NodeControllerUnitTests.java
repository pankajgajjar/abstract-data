package com.cs.delivery;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.delivery.NodeController;
import com.cs.interactions.DimensionService;
import com.cs.model.MultiDimensionalObject;

@RunWith(MockitoJUnitRunner.class)
public class NodeControllerUnitTests {

	private NodeController treeController;

	@Mock
	private DimensionService dimensionService;

	@Before
	public void setUp() {
		treeController = new NodeController(dimensionService);
	}

	

	@Test
	public void itShouldCreateANode() throws InterruptedException, IOException,
			URISyntaxException, ParseException {

		// given
		String type = "compaign";
		String name = "co01";
		boolean isFolder = true;
		String path = "-1";

		String expectedDimensionId = name;

		// when

		when(dimensionService.create(name, type, path, isFolder)).thenReturn(
				expectedDimensionId);

		String actualResponse = treeController.create(name, type, path,
				isFolder);
		// then
		verify(dimensionService).create(name, type, path, isFolder);
		assertThat(actualResponse).isEqualTo(expectedDimensionId);

	}

	@Test
	public void itShouldGetAllAvailableDimensions() throws IOException,
			URISyntaxException {
		// given

		String content = "expected";
		when(dimensionService.getAll()).thenReturn(content);
		// when

		String actualContent = treeController.getAllAvailable();
		// then
		verify(dimensionService).getAll();
		assertThat(actualContent).isEqualTo(content);
	}

	@Test
	public void itShouldGetAllDimensionsAccordingToStructure() {
		// given
		List<MultiDimensionalObject> models = new ArrayList<MultiDimensionalObject>();
		String structure = "C-MP-P";

		// when
		when(dimensionService.getAllBy(structure)).thenReturn(models);
		List<MultiDimensionalObject> actualModels = treeController
				.getDimensionsBy(structure);

		// then
		verify(dimensionService).getAllBy(structure);
		assertThat(actualModels).isEqualTo(models);

	}

}
