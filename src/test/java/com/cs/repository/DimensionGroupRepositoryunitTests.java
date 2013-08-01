package com.cs.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoOperations;

import com.cs.data.core.nosql.NoSqlOperations;
import com.cs.model.DimensionGroup;
import com.cs.model.DimensionGroupList;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DimensionGroupRepositoryunitTests {

	private DimensionGroupRepository repository;

	@Mock
	private MongoOperations mongoTemplate;

	@Before
	public void setup() {
		repository = new DimensionGroupRepository(mongoTemplate);
	}

	@Test
	public void itShouldSaveObjectToMongoDB() {

		// given

		String expectedResult = "test";
		DimensionGroup group = new DimensionGroup("101", "group1");

		// when

		repository.save(group);

		// then

		verify(mongoTemplate).insert(group);

	}

	@Test
	public void itShouldGetListOfObjectsFromMongoDB() {

		// given
		ArrayList<DimensionGroup> groups = new ArrayList<>();

		DimensionGroup group = new DimensionGroup("id", "group1");
		groups.add(group);
		DimensionGroupList expectedList = new DimensionGroupList();
		expectedList.setDimensiongroup(groups);
		// when

		when(mongoTemplate.findAll(DimensionGroup.class)).thenReturn(groups);
		List<DimensionGroup> actualList = repository.findBy("group1");
		// then
		verify(mongoTemplate).findAll(DimensionGroup.class);
		assertThat(actualList).isEqualTo(groups);

	}

}
