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
import com.cs.utils.ArrayUtils;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChapterRepositoryUnitTests {

	private ChapterRepository repository;

	@Mock
	private MongoRepository noSqlTemplateForMongo;

	@Mock
	private ViewStructureCache cache;

	ContentObject publication;

	@Before
	public void setUp() {
		repository = new ChapterRepository(noSqlTemplateForMongo, cache);
		publication = new ContentObject("Test", "publication", "A,B,C,D,E",
				"true");
		ContentObject test = new ContentObject("test01", "chapter",
				"A,B,C,D,E,publication", "false");
		test.addchild(new ContentObject("test03", "test", "test", "test"));
		publication.addchild(test);
		publication.addchild(new ContentObject("test02", "test", "A", "true"));

	}

	@Test
	public void itShouldCreateAChapterInTheParentPublication() {
		// given
		ContentObject chapter = new ContentObject("test", "test",
				"A,B,C,D,E,test03", "B");
		String result = "success";
		// when
		when(cache.getCurrentViewStructure()).thenReturn("C-M-P-D");
		when(noSqlTemplateForMongo.save(chapter)).thenReturn(result);
		when(noSqlTemplateForMongo.getObjectByKey("D", ContentObject.class))
				.thenReturn(publication);
		String actualResult = repository.save(chapter);

		// then
		verify(noSqlTemplateForMongo).getObjectByKey("D", ContentObject.class);
		verify(noSqlTemplateForMongo).save(chapter);
		System.out.println(publication);

	}

	@Test
	public void itShouldGetpublicationId() {
		//
		String path = "A,B,C,D,E,F";
		//
		String actualId = repository.getParentId(path);

		assertThat(actualId).isEqualTo("F");

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

	@Test
	public void itShouldFindContentObjectWithGivenId() {
		// given
		String id = "test01";

		// when
		ContentObject object = repository.find(publication, id);

		// then
		assertThat(object.getId()).isEqualTo(id);

	}

	@Test
	public void itShouldDeleteGivenNode() {
		// given

		String result = "result";
		String oldPath = "A,B,C,D,E,test03";
		ContentObject chapter = new ContentObject("test", "test", "A,B,C,D,E",
				"B");
		when(cache.getCurrentViewStructure()).thenReturn("C-M-P-D");
		when(noSqlTemplateForMongo.save(chapter)).thenReturn(result);
		when(noSqlTemplateForMongo.getObjectByKey("D", ContentObject.class))
				.thenReturn(publication);

		// when
		repository.delete(chapter, oldPath);

		// then
		verify(noSqlTemplateForMongo).getObjectByKey("D", ContentObject.class);
		verify(noSqlTemplateForMongo).save(publication);

	}

}
