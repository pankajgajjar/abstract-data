package com.cs.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.model.DimensionModel;
import com.cs.utils.FileUtils;

@Controller
public class DimensionController {

	private FileUtils utils;

	@Autowired
	public DimensionController(FileUtils utils) {
		this.utils = utils;
	}

	@RequestMapping(value = "/{dimensionId}", method = RequestMethod.GET)
	public DimensionController get(@PathVariable(value = "dimensionId") String dimensionId) {
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

		return utils.getFileContents("dimensions.json");

	}

}
