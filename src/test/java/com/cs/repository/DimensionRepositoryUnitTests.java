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

	private DimensionGroup group;

	@Before
	public void setUp() {

		dimensionRepository = new DimensionRepository(fileUtils, cache,
				repository);
		group = new DimensionGroup();

	}

	@Test
	public void itShouldCreateADimension() {

		// given
		String expectedDimensionId = "test01";
		List<DimensionModel> models = new ArrayList<DimensionModel>();
		models.add(new DimensionModel("test", "test", "test", "test"));
		group.setDimensions(models);
		// when
		when(cache.getDimensionGroupIdFor(dimensionModel)).thenReturn(
				expectedDimensionId);
		when(repository.find(expectedDimensionId, DimensionGroup.class))
				.thenReturn(group);

		String dimensionId = dimensionRepository
				.createDimension(dimensionModel);

		// then
		verify(cache).getDimensionGroupIdFor(dimensionModel);
		verify(repository).find(expectedDimensionId, DimensionGroup.class);
		assertThat(dimensionId).isEqualTo(dimensionModel.getId());
	}
}
