package com.cs.repository;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.cache.DimensionGroupCache;
import com.cs.data.core.nosql.mongodb.MongoRepository;
import com.cs.model.ContentObject;
import com.cs.utils.FileUtils;

@RunWith(MockitoJUnitRunner.class)
public class DimensionRepositoryUnitTests {
	private DimensionRepository dimensionRepository;

	@Mock
	private ContentObject dimensionModel;

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
		ContentObject dimension = new ContentObject("c01", "campaign",
				"co01","co01", "testPath");
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

	@Test
	public void itShouldGetAllDimensionInstances() {

		// given
		List<ContentObject> expectedModels = new ArrayList<ContentObject>();
		expectedModels.add(new ContentObject());
		// when
		when(repository.findAll(ContentObject.class)).thenReturn(
				expectedModels);
		List<ContentObject> dimensions = dimensionRepository.getDimensions();
		// then
		verify(repository).findAll(ContentObject.class);
		assertThat(dimensions).isNotEmpty();
		assertThat(dimensions).isEqualTo(expectedModels);

	}

	@Test
	public void itShouldGetDimensionsByType() {
		// given
		String type = "Campaign";
		// when

		dimensionRepository.getDimensionsOfType(type);
		// then

		verify(repository).getObjectsBy("type", type, ContentObject.class);
	}

	@Test
	public void itShouldReturnAllDimensionsByGroupIdsAndType() {
		// given

		List<String> groupIds = null;
		String type2 = null;
		// when
		dimensionRepository.getDimensionsBy(type2, groupIds);
		// then
		verify(repository).getObjectForAndCriteria("type", type2, "groupIds",
				groupIds, ContentObject.class);
	}

}
