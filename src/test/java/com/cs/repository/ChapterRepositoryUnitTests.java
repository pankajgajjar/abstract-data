package com.cs.repository;

import org.junit.Before;
import org.junit.Test;

import com.cs.model.ContentObject;

public class ChapterRepositoryUnitTests {

	private ChapterRepository repository;

	@Before
	public void setUp() {
		repository = new ChapterRepository();
	}

	@Test
	public void itShouldCreateAChapterInTheParentPublication() {
		// given
		ContentObject chapter = new ContentObject("test", "test", "A", "B");

		// when
		repository.save(chapter);

		// then

	}

}
