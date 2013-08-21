package com.cs.interactions;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.factory.DomainFactory;
import com.cs.interactions.ChapterService;
import com.cs.model.MultiDimensionalObject;
import com.cs.repository.ChapterRepository;

@RunWith(MockitoJUnitRunner.class)
public class ChapterServiceUnitTests {

	private ChapterService service;

	@Mock
	private ChapterRepository chapterRepository;

	@Mock
	private DomainFactory factory;

	@Before
	public void setUp() {
		service = new ChapterService(chapterRepository, factory);

	}

	@Test
	public void itShouldCreateAChapter() {

		// given

		String result = "success";
		String name = "test";
		String path = "A,B";
		String type = "spread";
		boolean isFolder = true;
		// when
		MultiDimensionalObject object = new MultiDimensionalObject();
		when(factory.getDomainObject("MultiDimensionalObject")).thenReturn(
				object);
		when(chapterRepository.save(object)).thenReturn(result);

		String actualResult = service.create(type, name, path, isFolder);

		// then
		verify(chapterRepository).save(object);
		assertThat(actualResult).isEqualTo(actualResult);

	}

	@Test
	public void itShouldDeleteAChapter() {
		// given
		String result = "success";
		String oldPath = "testpath";
		MultiDimensionalObject object = new MultiDimensionalObject();
		when(chapterRepository.delete(object, oldPath)).thenReturn(result);
		// when
		service.delete(object, oldPath);

		// then
		verify(chapterRepository).delete(object, oldPath);

	}

}
