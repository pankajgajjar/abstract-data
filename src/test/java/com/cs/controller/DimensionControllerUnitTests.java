package com.cs.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.controller.DimensionController;
import com.cs.model.CustomResponse;
import com.cs.model.DimensionGroup;
import com.cs.model.DimensionModel;
import com.cs.service.DimensionService;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DimensionControllerUnitTests {

	private DimensionController treeController;

	@Mock
	private DimensionService dimensionService;

	@Mock
	private DimensionModel dimensionModel;

	@Mock
	private DimensionGroup dimensionGroup;

	@Before
	public void setUp() {
		treeController = new DimensionController(dimensionService,
				dimensionModel);
	}

	@Test
	public void itShouldReturnIndexPage() {
		// given
		String expectedPage = "redirect:/pages/index.html";

		// when
		String actualPage = treeController.getIndexPage();

		// then

		assertThat(actualPage).isEqualTo(expectedPage);

	}

	@Test
	public void itShouldCreateANode() throws InterruptedException, IOException,
			URISyntaxException, ParseException {

		// given
		String type = "compaign";
		String name = "co01";
		String parentId = "-1";
		String path = "-1";

		String expectedDimensionId = name;

		// when

		when(dimensionService.createDimension(dimensionModel)).thenReturn(
				expectedDimensionId);

		String actualResponse = treeController.create(type, name, path,
				parentId);
		// then
		verify(dimensionService).createDimension(dimensionModel);
		assertThat(actualResponse).isEqualTo(expectedDimensionId);

	}

	@Test
	public void itShouldGetAllAvailableDimensions() throws IOException,
			URISyntaxException {
		// given

		String content = "expected";
		when(dimensionService.getAllDimensions()).thenReturn(content);
		// when

		String actualContent = treeController.getAllAvailable();
		// then
		verify(dimensionService).getAllDimensions();
		assertThat(actualContent).isEqualTo(content);
	}
}
