package com.cs.cache;

import static org.junit.Assert.*;

import javax.annotation.meta.When;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.cache.DimensionGroupCache;
import com.cs.data.core.nosql.redis.RedisRepository;
import com.cs.model.DimensionModel;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DimensionGroupCacheTests {

	private DimensionModel dimensionModel;
	private DimensionGroupCache groupCache;

	@Mock
	private RedisRepository redisRepository;

	@Before
	public void setUp() {

		groupCache = new DimensionGroupCache(redisRepository);
		dimensionModel = new DimensionModel("test01", "campaign", "co01", "-1");
	}

	@Test
	public void itShouldGetDimensionGroupIdForGivenDimension() {

		// given

		String expectedId = "group01";
		// when

		when(redisRepository.get(dimensionModel.getPath())).thenReturn(
				expectedId);
		String actualGroupId = groupCache
				.getDimensionGroupIdFor(dimensionModel);
		// then
		verify(redisRepository).get(dimensionModel.getPath());
		assertThat(actualGroupId).isEqualTo(expectedId);
	}

	@Test
	public void itShouldUpdateDimensionGroupIdForGivenDimension() {
		// given
		String key = "key";
		String value = "value";
		String groupId = "abc@123";
		// when
		groupCache.updateCache(dimensionModel, groupId);

		// then
		verify(redisRepository).delete(dimensionModel.getPath());
		verify(redisRepository).set(dimensionModel.getPath().concat(","+dimensionModel.getName()), groupId);
	}

	@Test
	public void itShouldSetDimensionGroupIdForGivenDimension() {
		// given
		String key = "key";
		String value = "value";
		String exptectedResult = "test";
		String groupId = "abc@123";
		// when
		groupCache.addNewGroup(dimensionModel, groupId);

		// then
		verify(redisRepository).set(dimensionModel.getPath().concat(","+dimensionModel.getName()), groupId);

	}
	
}
