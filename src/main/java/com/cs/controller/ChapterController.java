package com.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.cs.factory.DomainFactory;
import com.cs.model.ContentObject;
import com.cs.service.IService;

public class ChapterController {

	private IService chapterService;
	private DomainFactory factory;
	private final String CONTENTOBJECT = "ContentObject";

	@Autowired
	public ChapterController(IService chapterService, DomainFactory factory) {
		this.chapterService = chapterService;
		this.factory = factory;
	}

	public String create(String type, String name, String path, String isFolder) {
		ContentObject chapter = (ContentObject) factory
				.getDomainObject(CONTENTOBJECT);
		return chapterService.create(chapter);

	}

	private ContentObject setChapterAtrributes(String type, String name,
			String path, String isFolder) {
		return new ContentObject(name, type, path, isFolder);
	}

}
