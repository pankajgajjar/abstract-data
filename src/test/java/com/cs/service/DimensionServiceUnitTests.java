package com.cs.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.model.DimensionModel;
import com.cs.repository.DimensionRepository;
import com.cs.utils.FileUtils;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DimensionServiceUnitTests {

	private DimensionService dimensionService;

	@Mock
	private FileUtils fileUtils;

	@Mock
	DimensionModel dimensionModel;

	@Mock
	DimensionRepository dimensionRepository;

	@Before
	public void setUp() {
		dimensionService = new DimensionService(fileUtils, dimensionRepository);
	}

	@Test
	public void itShouldGetAllDimensions() throws IOException,
			URISyntaxException {

		// given
		String expected = "testString";

		// when
		when(fileUtils.getFileContents("dimensions.json")).thenReturn(expected);
		String dimensions = dimensionService.getAllDimensions();

		// then

		verify(fileUtils).getFileContents("dimensions.json");
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

}
