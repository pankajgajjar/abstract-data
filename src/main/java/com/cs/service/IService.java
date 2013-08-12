package com.cs.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.cs.data.core.GenericDomain;
import com.cs.model.ContentObject;

/**
 * The Interface IService.
 */
public interface IService {

	/**
	 * Gets the all.
	 * 
	 * @return the all
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             the uRI syntax exception
	 */
	public String getAll() throws IOException, URISyntaxException;

	/**
	 * Creates the.
	 * 
	 * @param isFolder
	 * @param path
	 * @param name
	 * @param type
	 * @return the string
	 */
	public String create(String type, String name, String path, String isFolder);

	/**
	 * Gets the all by.
	 * 
	 * @param structure
	 *            the structure
	 * @return the all by
	 */
	public List<ContentObject> getAllBy(String structure);

	/**
	 * Move.
	 * 
	 * @param chapter
	 *            the chapter
	 * @param path
	 *            the path
	 */
	void move(String type, String name, String path, String isFolder,
			String newpath);

	/**
	 * Delete.
	 * 
	 * @param chapter
	 *            the chapter
	 * @param path
	 *            the path
	 */
	void delete(ContentObject chapter, String path);

}