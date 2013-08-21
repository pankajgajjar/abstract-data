package com.cs.delivery;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.interactions.Service;
import com.cs.model.MultiDimensionalObject;

/**
 * The Class NodeController.
 */
@Controller
public class NodeController {

	/** The Constant CREATE. */
	private static final String CREATE = "/dimension/create/{type}/name/{name}/path/{path}/folder/{folder}";

	/** The dimension service. */
	private Service dimensionService;


	/**
	 * Instantiates a new node controller.
	 * 
	 * @param dimensionService
	 *            the dimension service
	 * @param factory
	 *            the factory
	 * @param cache
	 *            the cache
	 */
	@Autowired
	public NodeController(Service dimensionService) {
		this.dimensionService = dimensionService;

	}

	/**
	 * Creates the Dimension Model.
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
	@RequestMapping(value = { CREATE }, method = RequestMethod.GET)
	public @ResponseBody
	String create(@PathVariable("type") String type,
			@PathVariable("name") String name,
			@PathVariable("path") String path,
			@PathVariable("folder") boolean isFolder) {

		return dimensionService.create(type, name, path, isFolder);

	}

	/**
	 * Gets the Dimension using the given id.
	 * 
	 * @param dimensionId
	 *            the dimension id
	 * @return the node controller
	 */
	@RequestMapping(value = "/{dimensionId}", method = RequestMethod.GET)
	public @ResponseBody
	NodeController get(@PathVariable(value = "dimensionId") String dimensionId) {
		return null;

	}

	/**
	 * Gets the dimensions by the given structure.
	 * 
	 * @param structure
	 *            the structure
	 * @return the dimensions by
	 */
	@RequestMapping(value = "/dimension/get/{structure}")
	public @ResponseBody
	List<MultiDimensionalObject> getDimensionsBy(@PathVariable String structure) {

		return dimensionService.getAllBy(structure);
	}

	/**
	 * Gets the all available Dimensions.
	 * 
	 * @return the all available
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             the uRI syntax exception
	 */
	@RequestMapping(value = "/dimension/alldimensions")
	public @ResponseBody
	String getAllAvailable() throws IOException, URISyntaxException {
		return dimensionService.getAll();

	}

}