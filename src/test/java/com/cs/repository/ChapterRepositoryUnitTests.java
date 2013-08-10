package com.cs.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.cache.DimensionGroupCache;
import com.cs.cache.ViewStructureCache;
import com.cs.data.core.nosql.mongodb.MongoRepository;
import com.cs.model.ContentObject;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChapterRepositoryUnitTests {

	private ChapterRepository repository;

	@Mock
	private MongoRepository noSqlTemplateForMongo;

	@Mock
	private ViewStructureCache cache;

	@Before
	public void setUp() {
		repository = new ChapterRepository(noSqlTemplateForMongo, cache);
	}

	@Test
	public void itShouldCreateAChapterInTheParentPublication() {
		// given
		ContentObject chapter = new ContentObject("test", "test",
				"A,B,C,D,E,F", "B");

		String result = "success";
		// when
		when(cache.getCurrentViewStructure()).thenReturn("C-M-P-D");
		when(noSqlTemplateForMongo.save(chapter)).thenReturn(result);
		String actualResult = repository.save(chapter);

		// then
		verify(noSqlTemplateForMongo).save(chapter);

	}

	@Test
	public void itShouldGetPublicationIdForGivenPath() {
		// given
		String path = "A,B,C,D,E";
		// when
		when(cache.getCurrentViewStructure()).thenReturn("C-M-P-D");
		String actualPublicationId = repository.getPublicationId(path);
		// then
		verify(cache).getCurrentViewStructure();
		assertThat(actualPublicationId).isEqualTo("D");

	}

	@Test
	public void itShouldGetLastIndexOfCurrentViewStructure() {
		String viewStructure = "C-M-D-P";
		int index = repository.getLastIndexOf(viewStructure);
		assertThat(index).isEqualTo(3);
	}

}
