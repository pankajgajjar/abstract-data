package com.cs.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import com.cs.model.DimensionGroup;
import com.cs.model.DimensionModel;

public class DimensionRepositoryUnitTests {
	private DimensionRepository dimensionRepository;

	@Mock
	private DimensionModel dimensionModel;

	@Mock
	private DimensionGroup dimensionGroup;

	@Before
	public void setUp() {

		dimensionRepository = new DimensionRepository(dimensionGroup);

	}

	@Test
	public void itShouldCreateADimension() {

		// given
		String expectedDimensionId = "test01";
		// when
		String dimensionId = dimensionRepository
				.createDimension(dimensionModel);

		// then

		assertThat(dimensionId).isEqualTo(expectedDimensionId);
	}
}
