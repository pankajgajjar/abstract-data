package com.cs.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.cache.ViewStructureCache;
import com.cs.data.core.GenericDomain;
import com.cs.factory.DomainFactory;
import com.cs.model.ContentObject;
import com.cs.service.IService;


/**
 * The Class NodeController.
 */
@Controller
public class NodeController {

	/** The Constant KEY. */
	private static final String KEY = "view";
	
	/** The Constant CREATE. */
	private static final String CREATE = "/dimension/create/{type}/name/{name}/path/{path}/folder/{folder}";
	
	/** The dimension service. */
	private IService dimensionService;
	
	/** The factory. */
	private DomainFactory factory;
	
	/** The cache. */
	private ViewStructureCache cache;
	
	/** The contentobject. */
	private final String CONTENTOBJECT = "ContentObject";
	
	/** The index. */
	private final String INDEX = "redirect:/pages/index.html";

	/**
	 * Instantiates a new node controller.
	 *
	 * @param dimensionService the dimension service
	 * @param factory the factory
	 * @param cache the cache
	 */
	@Autowired
	public NodeController(IService dimensionService, DomainFactory factory,
			ViewStructureCache cache) {
		this.dimensionService = dimensionService;
		this.factory = factory;
		this.cache = cache;

	}

	/**
	 * Redirects to the the index page.
	 *
	 * @return the index page
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String getIndexPage() {
		return INDEX;
	}

	/**
	 * Creates the Dimension Model.
	 *
	 * @param type the type
	 * @param name the name
	 * @param path the path
	 * @param isFolder the is folder
	 * @return the string
	 */
	@RequestMapping(value = { CREATE }, method = RequestMethod.GET)
	public @ResponseBody
	String create(@PathVariable("type") String type,
			@PathVariable("name") String name,
			@PathVariable("path") String path,
			@PathVariable("folder") String isFolder) {

		ContentObject dimension = (ContentObject) factory
				.getDomainObject(CONTENTOBJECT);

		setDimensionAttributes(dimension, type, name, path, isFolder);
		return dimensionService.create(dimension);

	}

	/**
	 * Sets the dimension attributes.
	 *
	 * @param dimension the dimension
	 * @param type the type
	 * @param name the name
	 * @param path the path
	 * @param isFolder the is folder
	 */
	protected void setDimensionAttributes(ContentObject dimension, String type,
			String name, String path, String isFolder) {
		dimension.setId(name);
		dimension.setTitle(name);
		dimension.setIsFolder(isFolder);
		dimension.setPath(path);
		dimension.setName(name);
		dimension.setType(type);


	}

	/**
	 * Gets the Dimension using the given id.
	 *
	 * @param dimensionId the dimension id
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
	 * @param structure the structure
	 * @return the dimensions by
	 */
	@RequestMapping(value = "/dimension/get/{structure}")
	public @ResponseBody
	List<ContentObject> getDimensionsBy(@PathVariable String structure) {
		setCurrentViewStructure(structure);
		return dimensionService.getAllBy(structure);
	}

	/**
	 * Gets the all available Dimensions.
	 *
	 * @return the all available
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws URISyntaxException the uRI syntax exception
	 */
	@RequestMapping(value = "/dimension/alldimensions")
	public @ResponseBody
	String getAllAvailable() throws IOException, URISyntaxException {
		return dimensionService.getAll();

	}

	/**
	 * Sets the current view structure.
	 *
	 * @param currentViewStructure the new current view structure
	 */
	protected void setCurrentViewStructure(String currentViewStructure) {
		cache.setCurrentViewStructure(KEY, currentViewStructure);

	}

}