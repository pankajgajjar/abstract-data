package com.cs.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.factory.DomainFactory;
import com.cs.model.ContentObject;
import com.cs.service.ChapterService;
import com.cs.service.IService;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChapterControllerUnitTests {

	private ChapterController chapterController;

	@Mock
	private ChapterService chapterService;

	@Mock
	private DomainFactory factory;

	private ContentObject dimension;

	@Before
	public void setUp() {
		chapterController = new ChapterController(chapterService, factory);

	}

	@Test
	public void itShouldCallChapterServiceToCreateAChapter() {

		// given
		String name = "test";
		String path = "A,B";
		String type = "spread";
		String isFolder = "true";

		ContentObject chapter = new ContentObject(name, type, path, isFolder);

		// when
		when(factory.getDomainObject("ContentObject")).thenReturn(chapter);
		when(chapterService.create(chapter)).thenReturn(name);
		String actualName = chapterController
				.create(type, name, path, isFolder);
		// then
		verify(chapterService).create(chapter);
		assertThat(actualName).isEqualTo(name);

	}

	@Test
	public void itShouldChapterServiceToMoveAnObjectToGivenLocation() {

		// given
		String currentPath = "A-B-C";
		String newPath = "A-B-D";
		String name = "test";
		String path = "A,B";
		String type = "spread";
		String isFolder = "true";

		ContentObject chapter = new ContentObject(name, type, path, isFolder);

		// when
		when(factory.getDomainObject("ContentObject")).thenReturn(chapter);
		chapterController.move(type, name, path, isFolder, newPath);

		// then
		verify(chapterService).move(chapter, path);
	}
}
