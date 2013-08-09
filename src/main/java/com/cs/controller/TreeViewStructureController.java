package com.cs.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.utils.FileUtils;

@Controller
public class TreeViewStructureController {

	private FileUtils utils;

	@Autowired
	public TreeViewStructureController(FileUtils utils) {
		this.utils = utils;
	}

	@RequestMapping("/{structureId}")
	public @ResponseBody
	Object get(@PathVariable String structureId) throws IOException,
			URISyntaxException, ParseException {

		JSONParser parser=new JSONParser();
		
		return parser.parse(utils.getFileContents("schema" + structureId + ".json")); //$NON-NLS-1$ //$NON-NLS-2$

	}

	@RequestMapping("/default")
	public @ResponseBody
	String getDefault() throws IOException, URISyntaxException {

		return utils.getFileContents("schema1.json"); //$NON-NLS-1$

	}

	@RequestMapping("/all")
	public @ResponseBody
	String getAll() throws IOException, URISyntaxException {
		return utils.getFileContents("allSchema.json");
	}

}
