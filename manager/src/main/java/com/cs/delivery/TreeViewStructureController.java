package com.cs.delivery;

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


/**
 * The Class TreeViewStructureController.
 */
@Controller
public class TreeViewStructureController {

	/** The utils. */
	private FileUtils utils;

	/**
	 * Instantiates a new tree view structure controller.
	 *
	 * @param utils the utils
	 */
	@Autowired
	public TreeViewStructureController(FileUtils utils) {
		this.utils = utils;
	}

	/**
	 * Gets the requested view structure.
	 *
	 * @param structureId the structure id
	 * @return the object
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws URISyntaxException the uRI syntax exception
	 * @throws ParseException the parse exception
	 */
	@RequestMapping("/treeviewstructure/{structureId}")
	public @ResponseBody
	Object get(@PathVariable String structureId) throws IOException,
			URISyntaxException, ParseException {

		JSONParser parser=new JSONParser();
		
		return parser.parse(utils.getFileContents("schema" + structureId + ".json")); //$NON-NLS-1$ //$NON-NLS-2$

	}

	/**
	 * Gets the default view structure.
	 *
	 * @return the default
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws URISyntaxException the uRI syntax exception
	 */
	@RequestMapping("/treeviewstructure/default")
	public @ResponseBody
	String getDefault() throws IOException, URISyntaxException {

		return utils.getFileContents("schema1.json"); //$NON-NLS-1$

	}

	/**
	 * Gets all the tree structure.
	 *
	 * @return the all
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws URISyntaxException the uRI syntax exception
	 */
	@RequestMapping("/all")
	public @ResponseBody
	String getAll() throws IOException, URISyntaxException {
		return utils.getFileContents("allSchema.json");
	}

}
