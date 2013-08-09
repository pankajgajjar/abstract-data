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

import com.cs.model.DimensionModel;
import com.cs.service.DimensionService;

@Controller
public class NodeController {

	private DimensionService dimensionService;

	@Autowired
	public NodeController(DimensionService dimensionService) {
		this.dimensionService = dimensionService;

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
		DimensionModel dimension = new DimensionModel();
		dimension.setId(name);
		dimension.setName(name);
		dimension.setType(type);
		dimension.setPath(path);
		dimension.setTitle(name);
		dimension.setIsFolder(isFolder);
		System.out.println(dimension);
		return dimensionService.createDimension(dimension);

	}

	@RequestMapping(value = { "/createTest" })
	public @ResponseBody
	String createTest() {
		return "test";

	}

	@RequestMapping(value = "/{dimensionId}", method = RequestMethod.GET)
	public @ResponseBody
	NodeController get(@PathVariable(value = "dimensionId") String dimensionId) {
		return null;

	}

	@RequestMapping(value = "/dimension/get/{structure}")
	public @ResponseBody
	List<DimensionModel> getDimensionsBy(@PathVariable String structure) {
		return dimensionService.getDimensionsByStructure(structure);
	}

	@RequestMapping(value = "/dimension/alldimensions")
	public @ResponseBody
	String getAllAvailable() throws IOException, URISyntaxException {
		return dimensionService.getAllDimensions();

	}

}