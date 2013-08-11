package com.cs.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.model.ContentObject;
import com.cs.repository.ChapterRepository;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChapterServiceUnitTests {

	private ChapterService service;

	@Mock
	private ChapterRepository chapterRepository;

	@Before
	public void setUp() {
		service = new ChapterService(chapterRepository);

	}

	@Test
	public void itShouldCreateAChapter() {
		// given

		String result = "success";
		// when
		ContentObject object = new ContentObject();
		when(chapterRepository.save(object)).thenReturn(result);
		String actualResult = service.create(object);

		// then
		verify(chapterRepository).save(object);
		assertThat(actualResult).isEqualTo(actualResult);

	}

	@Test
	public void itShouldDeleteAChapter() {
		// given
		String result = "success";
		String oldPath = "testpath";
		ContentObject object = new ContentObject();
		when(chapterRepository.delete(object, oldPath)).thenReturn(result);
		// when
		service.delete(object, oldPath);

		// then
		verify(chapterRepository).delete(object, oldPath);

	}

}
