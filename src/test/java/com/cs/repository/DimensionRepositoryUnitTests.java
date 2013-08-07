package com.cs.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import com.cs.cache.DimensionGroupCache;
import com.cs.data.core.nosql.mongodb.MongoRepository;
import com.cs.model.DimensionGroup;
import com.cs.model.DimensionModel;
import com.cs.utils.FileUtils;

@RunWith(MockitoJUnitRunner.class)
public class DimensionRepositoryUnitTests {
	private DimensionRepository dimensionRepository;

	@Mock
	private DimensionModel dimensionModel;

	@Mock
	private FileUtils fileUtils;

	@Mock
	private DimensionGroupCache cache;

	@Mock
	private MongoRepository repository;

	@Before
	public void setUp() {

		dimensionRepository = new DimensionRepository(fileUtils, cache,
				repository);

	}

	@Test
	public void itShouldCreateADimension() {

		// given
		String dimensionId = "test";
		String groupId = "group";
		String path = "testPath";
		DimensionModel dimension = new DimensionModel("c01", "campaign",
				"co01", "testPath");
		// when
		when(cache.ifGroupIdExistsFor(dimension.getPath())).thenReturn(true);
		when(cache.getDimensionGroupIdFor(dimension.getPath())).thenReturn(
				groupId);
		String actualId = dimensionRepository.createDimension(dimension);

		// then
		verify(cache).ifGroupIdExistsFor(dimension.getPath());
		verify(cache).updateCache(dimension, groupId);
		assertThat(actualId).isEqualTo(dimension.getId());

	}

}