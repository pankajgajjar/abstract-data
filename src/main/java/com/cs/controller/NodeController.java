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

import com.cs.data.core.GenericDomain;
import com.cs.factory.DomainFactory;
import com.cs.model.ContentObject;
import com.cs.service.IService;

@Controller
public class NodeController {

	private IService dimensionService;
	private DomainFactory factory;
	private final String CONTENTOBJECT = "ContentObject";

	@Autowired
	public NodeController(IService dimensionService, DomainFactory factory) {
		this.dimensionService = dimensionService;
		this.factory = factory;

	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String getIndexPage() {
		return "redirect:/pages/index.html";
	}

	@RequestMapping(value = { "/dimension/create/{type}/name/{name}/path/{path}/folder/{folder}" }, method = RequestMethod.GET)
	public @ResponseBody
	String create(@PathVariable("type") String type,
			@PathVariable("name") String name,
			@PathVariable("path") String path,
			@PathVariable("folder") String isFolder) {

		ContentObject dimension = (ContentObject) factory
				.getDomainObject(CONTENTOBJECT);
		

		setDimensionAttributes(dimension,type,name,path,isFolder);
		return dimensionService.create(dimension);

	}

	protected void setDimensionAttributes(ContentObject dimension, String type, String name, String path,
			String isFolder) {

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
		return dimensionService.getAllBy(structure);
	}

	@RequestMapping(value = "/dimension/alldimensions")
	public @ResponseBody
	String getAllAvailable() throws IOException, URISyntaxException {
		return dimensionService.getAll();

	}

}