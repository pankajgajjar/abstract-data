package com.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.factory.DomainFactory;
import com.cs.model.ContentObject;
import com.cs.service.IService;

@Controller
public class ChapterController {

	private static final String CREATE = "/chapter/create/{type}/name/{name}/path/{path}/folder/{folder}";
	private IService chapterService;
	private DomainFactory factory;
	private final String CONTENTOBJECT = "ContentObject";

	@Autowired
	public ChapterController(IService chapterService, DomainFactory factory) {
		this.chapterService = chapterService;
		this.factory = factory;
	}

	@RequestMapping(value = { CREATE })
	public @ResponseBody
	String create(@PathVariable("type") String type,
			@PathVariable("name") String name,
			@PathVariable("path") String path,
			@PathVariable("isFolder") String isFolder) {
		ContentObject chapter = (ContentObject) factory
				.getDomainObject(CONTENTOBJECT);
		setChapterAtrributes(chapter, type, name, path, isFolder);
		return chapterService.create(chapter);

	}

	private void setChapterAtrributes(ContentObject chapter, String type,
			String name, String path, String isFolder) {
		chapter.setId(name);
		chapter.setTitle(name);
		chapter.setIsFolder(isFolder);
		chapter.setPath(path);
		chapter.setName(name);
		chapter.setType(type);

	}

	public void move(String type, String name, String path, String isFolder,
			String newPath) {

		ContentObject chapter = (ContentObject) factory
				.getDomainObject(CONTENTOBJECT);
		setChapterAtrributes(chapter, type, name, newPath, isFolder);
		chapterService.move(chapter, path);

	}
}
