package com.cs.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.builder.TreeBuilder;
import com.cs.cache.ViewStructureCache;
import com.cs.factory.DomainFactory;
import com.cs.model.ContentObject;
import com.cs.repository.DimensionRepository;
import com.cs.utils.FileUtils;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DimensionServiceUnitTests {

	private DimensionService dimensionService;

	@Mock
	private ContentObject dimensionModel;

	@Mock
	private TreeBuilder treeBuilder;

	@Mock
	private DimensionRepository dimensionRepository;

	@Mock
	private DomainFactory factory;

	@Mock
	private ViewStructureCache cache;

	@Before
	public void setUp() {
		dimensionService = new DimensionService(dimensionRepository,
				treeBuilder, factory, cache);
	}

	@Test
	public void itShouldGetAllDimensions() throws IOException,
			URISyntaxException {

		// given
		String expected = "testString";

		// when
		when(dimensionRepository.getAllDimensions()).thenReturn(expected);
		String dimensions = dimensionService.getAll();

		// then

		verify(dimensionRepository).getAllDimensions();
		assertThat(dimensions).isEqualToIgnoringCase(expected);

	}

	@Test
	public void itShouldCreateADimension() {
		// given
		String expectedDimensionId = "dimkension01";
		String name = "test";
		String path = "A,B";
		String type = "spread";
		String isFolder = "true";

		// when
		ContentObject test = new ContentObject();
		when(dimensionRepository.createDimension(test)).thenReturn(name);
		when(factory.getDomainObject("ContentObject")).thenReturn(test);
		String dimensionId = dimensionService
				.create(type, name, path, isFolder);

		// then
		verify(dimensionRepository).createDimension(test);
		assertThat(dimensionId).isEqualTo(name);

	}

	@Test
	public void itShouldGetDimensionsByStructure() {
		// given

		String structure = "C-MP-P";
		// when
		List<ContentObject> models = dimensionService.getAllBy(structure);

		// then
		verify(cache).setCurrentViewStructure("view", structure);
		verify(treeBuilder).buildTree(structure);
	}

	@Test
	public void itShouldSaveCurrentViewStructureToCache() {
		// given
		String currentViewStructure = "C-M-P-D";
		// when

		dimensionService.setCurrentViewStructure(currentViewStructure);

		// then
		verify(cache).setCurrentViewStructure("view", currentViewStructure);
	}

}
