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

@Controller
public class NodeController {

	private static final String KEY = "view";
	private static final String CREATE = "/dimension/create/{type}/name/{name}/path/{path}/folder/{folder}";
	private IService dimensionService;
	private DomainFactory factory;
	private ViewStructureCache cache;
	private final String CONTENTOBJECT = "ContentObject";
	private final String INDEX = "redirect:/pages/index.html";

	@Autowired
	public NodeController(IService dimensionService, DomainFactory factory,
			ViewStructureCache cache) {
		this.dimensionService = dimensionService;
		this.factory = factory;
		this.cache = cache;

	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String getIndexPage() {
		return INDEX;
	}

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

	protected void setDimensionAttributes(ContentObject dimension, String type,
			String name, String path, String isFolder) {
		dimension.setId(name);
		dimension.setTitle(name);
		dimension.setIsFolder(isFolder);
		dimension.setPath(path);
		dimension.setName(name);
		dimension.setType(type);


	}

	@RequestMapping(value = "/{dimensionId}", method = RequestMethod.GET)
	public @ResponseBody
	NodeController get(@PathVariable(value = "dimensionId") String dimensionId) {
		return null;

	}

	@RequestMapping(value = "/dimension/get/{structure}")
	public @ResponseBody
	List<ContentObject> getDimensionsBy(@PathVariable String structure) {
		setCurrentViewStructure(structure);
		return dimensionService.getAllBy(structure);
	}

	@RequestMapping(value = "/dimension/alldimensions")
	public @ResponseBody
	String getAllAvailable() throws IOException, URISyntaxException {
		return dimensionService.getAll();

	}

	protected void setCurrentViewStructure(String currentViewStructure) {
		cache.setCurrentViewStructure(KEY, currentViewStructure);

	}

}