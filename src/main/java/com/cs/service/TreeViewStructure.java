package com.cs.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.utils.FileUtils;

@Controller
public class TreeViewStructure {

	private FileUtils utils;

	@Autowired
	public TreeViewStructure(FileUtils utils) {
		this.utils = utils;
	}
	
	@RequestMapping("/{structureId}")
	public @ResponseBody String get(@PathVariable String structureId) throws IOException, URISyntaxException{
		
		return utils.getFileContents("schema"+structureId+".json"); //$NON-NLS-1$ //$NON-NLS-2$
		
	}
	
	@RequestMapping("/default")
	public @ResponseBody String getDefault() throws IOException, URISyntaxException{
		
		return utils.getFileContents("schema1.json"); //$NON-NLS-1$
		
	}

	

}
