package com.cs.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.cs.data.core.GenericDomain;
import com.cs.model.ContentObject;

/**
 * Interface that specifies basic set of service operations. Ideally all the
 * services should extend this interface. Implemented by all service classes.
 */
public interface Service {

	/**
	 * Method for finding all entities present in the system
	 * 
	 * @return the all
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             the uRI syntax exception
	 */
	public String getAll() throws IOException, URISyntaxException;

	/**
	 * Method for creating a new entity to the system
	 * 
	 * @param isFolder
	 * @param path
	 * @param name
	 * @param type
	 * @return the string
	 */
	public String create(String type, String name, String path, String isFolder);

	/**
	 * Method for get all entities satisfies given criteria
	 * 
	 * @param structure
	 *            the structure
	 * @return the all by
	 */
	public List<ContentObject> getAllBy(String structure);

	/**
	 * Method for moving entity from one location to another.
	 * 
	 * @param chapter
	 *            the chapter
	 * @param path
	 *            the path
	 */
	void move(String type, String name, String path, String isFolder,
			String newpath);

	/**
	 * Method for deleting an entity from the system
	 * 
	 * @param chapter
	 *            the chapter
	 * @param path
	 *            the path
	 */
	void delete(ContentObject chapter, String path);

}