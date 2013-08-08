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
import com.cs.model.DimensionModel;
import com.cs.repository.DimensionRepository;
import com.cs.utils.FileUtils;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DimensionServiceUnitTests {

	private DimensionService dimensionService;

	@Mock
	private DimensionModel dimensionModel;

	@Mock
	private TreeBuilder treeBuilder;

	@Mock
	private DimensionRepository dimensionRepository;

	@Before
	public void setUp() {
		dimensionService = new DimensionService(dimensionRepository,
				treeBuilder);
	}

	@Test
	public void itShouldGetAllDimensions() throws IOException,
			URISyntaxException {

		// given
		String expected = "testString";

		// when
		when(dimensionRepository.getAllDimensions()).thenReturn(expected);
		String dimensions = dimensionService.getAllDimensions();

		// then

		verify(dimensionRepository).getAllDimensions();
		assertThat(dimensions).isEqualToIgnoringCase(expected);

	}

	@Test
	public void itShouldCreateADimension() {
		// given
		String expectedDimensionId = "dimkension01";

		// when

		when(dimensionRepository.createDimension(dimensionModel)).thenReturn(
				expectedDimensionId);
		String dimensionId = dimensionService.createDimension(dimensionModel);

		// then
		assertThat(dimensionId).isEqualTo(expectedDimensionId);

	}

	@Test
	public void itShouldGetDimensionsByStructure() {
		// given

		String structure = "C-MP-P";
		// when
		List<DimensionModel> models = dimensionService
				.getDimensionsByStructure(structure);

		// then
		verify(treeBuilder).buildTree(structure);
	}

}
