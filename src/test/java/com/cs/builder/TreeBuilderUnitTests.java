package com.cs.builder;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.cache.DimensionGroupCache;
import com.cs.model.DimensionModel;
import com.cs.model.DimensionModelList;
import com.cs.repository.DimensionRepository;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TreeBuilderUnitTests {

	private TreeBuilder treeBuilder;

	@Mock
	private DimensionGroupCache cache;

	@Mock
	private DimensionRepository dimensionRepository;

	@Mock
	private DimensionModelList list;

	@Before
	public void setUp() {
		treeBuilder = new TreeBuilder(cache, dimensionRepository);
	}

	@Test
	public void itShouldParseGivenStringWithDelimeter() {
		// given
		String structure = "Campaign-MasterPublication-Publication";

		// when
		String[] rules = treeBuilder.getTypes(structure);
		// then
		assertThat(rules).isNotEmpty();
		assertThat(rules.length).isEqualTo(3);
	}

	@Test
	public void itShouldSaparateNotConnectedTrees() {

		// given
		String type = "Campaign";
		List<DimensionModel> result = new ArrayList<DimensionModel>();

		// when

		when(dimensionRepository.getDimensionsOfType(type)).thenReturn(result);
		List<DimensionModel> models = treeBuilder.getAllSeparatedTrees(type);
		// then
		verify(dimensionRepository).getDimensionsOfType(type);
		assertThat(models).isEqualTo(result);
	}
}
