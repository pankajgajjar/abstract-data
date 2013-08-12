package com.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.factory.DomainFactory;
import com.cs.model.ContentObject;
import com.cs.service.IService;


/**
 * The Class ChapterController.
 */
@Controller
public class ChapterController {

	/** The Constant CREATE. */
	private static final String CREATE = "/chapter/create/{type}/name/{name}/path/{path}/folder/{folder}";
	
	/** The chapter service. */
	private IService chapterService;
	
	/** The factory. */
	private DomainFactory factory;
	
	/** The contentobject. */
	private final String CONTENTOBJECT = "ContentObject";

	/**
	 * Instantiates a new chapter controller.
	 *
	 * @param chapterService the chapter service
	 * @param factory the factory
	 */
	@Autowired
	public ChapterController(IService chapterService, DomainFactory factory) {
		this.chapterService = chapterService;
		this.factory = factory;
	}

	/**
	 * Creates the.
	 *
	 * @param type the type
	 * @param name the name
	 * @param path the path
	 * @param isFolder the is folder
	 * @return the string
	 */
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

	/**
	 * Sets the chapter atrributes.
	 *
	 * @param chapter the chapter
	 * @param type the type
	 * @param name the name
	 * @param path the path
	 * @param isFolder the is folder
	 */
	private void setChapterAtrributes(ContentObject chapter, String type,
			String name, String path, String isFolder) {
		chapter.setId(name);
		chapter.setTitle(name);
		chapter.setIsFolder(isFolder);
		chapter.setPath(path);
		chapter.setName(name);
		chapter.setType(type);

	}

	/**
	 * Move.
	 *
	 * @param type the type
	 * @param name the name
	 * @param path the path
	 * @param isFolder the is folder
	 * @param newPath the new path
	 */
	public void move(String type, String name, String path, String isFolder,
			String newPath) {

		ContentObject chapter = (ContentObject) factory
				.getDomainObject(CONTENTOBJECT);
		setChapterAtrributes(chapter, type, name, newPath, isFolder);
		chapterService.move(chapter, path);

	}
}
