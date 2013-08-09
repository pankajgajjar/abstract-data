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

import com.cs.model.CustomResponse;
import com.cs.model.DimensionModel;
import com.cs.service.DimensionService;

@Controller
public class DimensionController {

	private DimensionService dimensionService;
	private DimensionModel dimension;

	@Autowired
	public DimensionController(DimensionService dimensionService,
			DimensionModel dimension) {
		this.dimensionService = dimensionService;
		this.dimension = dimension;

	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String getIndexPage() {
		return "redirect:/pages/index.html";
	}

	@RequestMapping(value = { "/create/{type}/name/{name}/parentId/{parentId}/path/{path}" }, method = RequestMethod.GET)
	public @ResponseBody
	String create(@PathVariable("type") String type,
			@PathVariable("name") String name,
			@PathVariable("path") String path,
			@PathVariable("parentId") String parentId) {

		dimension.setId(name);
		dimension.setName(name);
		dimension.setType(type);
		dimension.setPath(path);

		return dimensionService.createDimension(dimension);

	}

	@RequestMapping(value = "/{dimensionId}", method = RequestMethod.GET)
	public DimensionController get(
			@PathVariable(value = "dimensionId") String dimensionId) {
		return null;

	}

	@RequestMapping(value = "/get/{structure}")
	public @ResponseBody
	List<DimensionModel> getDimensionsBy(@PathVariable String structure) {
		return dimensionService.getDimensionsByStructure(structure);
	}

	@RequestMapping(value = "/all")
	public @ResponseBody
	String getAllAvailable() throws IOException, URISyntaxException {

		return dimensionService.getAllDimensions();

	}

}