package com.cs.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.data.core.IRepository;
import com.cs.data.core.nosql.mongodb.MongoRepository;
import com.cs.utils.FileUtils;

@RunWith(MockitoJUnitRunner.class)
public class DimensionRepositoryUnitTests {

	private DimensionRepository dimensionRepository;
	@Mock
	private FileUtils fileUtils;

	@Test
	public void itShouldReturnAllDimensionsAvailable() {
		// given
		String content = "test content";
		// when

	}

}
