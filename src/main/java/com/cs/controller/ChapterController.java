package com.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.service.Service;

/**
 * The Class ChapterController.
 * TODO. com.cs.business.ifacadeservices
 * 					controller -> common facade ->>>(|) ->i***interface call
 * 					ichapter ->Impl
 * 					idimension ->Impl
 */
@Controller
public class ChapterController {

	/** The Constant CREATE. */
	private static final String CREATE = "/chapter/create/{type}/name/{name}/path/{path}/folder/{folder}";

	/** The chapter service. */
	private Service chapterService;

	/**
	 * Instantiates a new chapter controller.
	 * 
	 * @param chapterService
	 *            the chapter service
	 * @param factory
	 *            the factory
	 */
	@Autowired
	public ChapterController(Service chapterService) {
		this.chapterService = chapterService;

	}

	/**
	 * Creates the.
	 * 
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param path
	 *            the path
	 * @param isFolder
	 *            the is folder
	 * @return the string
	 */
	@RequestMapping(value = { CREATE })
	public @ResponseBody
	String create(@PathVariable("type") String type,
			@PathVariable("name") String name,
			@PathVariable("path") String path,
			@PathVariable("isFolder") String isFolder) {

		return chapterService.create(type, name, path, isFolder);

	}

	/**
	 * Move.
	 * 
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param path
	 *            the path
	 * @param isFolder
	 *            the is folder
	 * @param newPath
	 *            the new path
	 */
	public void move(String type, String name, String path, String isFolder,
			String newPath) {

		
		chapterService.move(type,name,path,isFolder,newPath);

	}
}
