package com.cs.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.model.CustomResponse;
import com.cs.model.DimensionGroup;
import com.cs.model.DimensionModel;
import com.cs.model.Tree;
import com.cs.repository.TreeRepository;
import com.cs.service.DimensionService;
import com.cs.utils.FileUtils;

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

		return dimensionService.createDimension(dimension);

	}

	@RequestMapping(value = "/{dimensionId}", method = RequestMethod.GET)
	public DimensionController get(
			@PathVariable(value = "dimensionId") String dimensionId) {
		return null;

	}

	public void create() {

	}

	public List<DimensionModel> list() {

		return new ArrayList<DimensionModel>();
	}

	@RequestMapping(value = "/all")
	public @ResponseBody
	String getAllAvailable() throws IOException, URISyntaxException {

		return dimensionService.getAllDimensions();

	}

}