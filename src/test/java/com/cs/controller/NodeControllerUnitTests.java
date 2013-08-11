package com.cs.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cs.cache.ViewStructureCache;
import com.cs.controller.NodeController;
import com.cs.factory.DomainFactory;
import com.cs.model.CustomResponse;
import com.cs.model.DimensionGroup;
import com.cs.model.ContentObject;
import com.cs.service.DimensionService;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NodeControllerUnitTests {

	private NodeController treeController;

	@Mock
	private DimensionService dimensionService;

	@Mock
	private ContentObject dimensionModel;

	@Mock
	private DomainFactory domainFactory;
	
	@Mock
	private ViewStructureCache cache;

	@Before
	public void setUp() {
		treeController = new NodeController(dimensionService, domainFactory,cache);
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

		when(domainFactory.getDomainObject("ContentObject")).thenReturn(
				dimensionModel);
		when(dimensionService.create(dimensionModel)).thenReturn(
				expectedDimensionId);

		String actualResponse = treeController.create(type, name, path,
				parentId);
		// then
		verify(dimensionService).create(dimensionModel);
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
		List<ContentObject> models = new ArrayList<ContentObject>();
		String structure = "C-MP-P";

		// when
		when(dimensionService.getAllBy(structure)).thenReturn(models);
		List<ContentObject> actualModels = treeController
				.getDimensionsBy(structure);

		// then
		verify(dimensionService).getAllBy(structure);
		verify(cache).setCurrentViewStructure("view", structure);
		assertThat(actualModels).isEqualTo(models);

	}

	@Test
	public void itShouldSaveCurrentViewStructureToCache() {
		// given
		String currentViewStructure = "C-M-P-D";
		// when

		treeController.setCurrentViewStructure(currentViewStructure);

		// then
		verify(cache).setCurrentViewStructure("view", currentViewStructure);
	}
	
	
}
