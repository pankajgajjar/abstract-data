package com.cs.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.model.CustomResponse;
import com.cs.utils.FileUtils;


/**
 * The Class ClientController.
 */
@Controller
public class ClientController {

	/** The file utils. */
	private FileUtils fileUtils;

	/**
	 * Instantiates a new client controller.
	 *
	 * @param fileUtils the file utils
	 */
	@Autowired
	public ClientController(FileUtils fileUtils) {
		// TODO Auto-generated constructor stub
		this.fileUtils = fileUtils;
	}

	/**
	 * Redirects to the Home page.
	 *
	 * @return the home
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws URISyntaxException the uRI syntax exception
	 */
	@RequestMapping(value = { "/ home" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	CustomResponse getHome() throws IOException, URISyntaxException {
		CustomResponse customResponse = new CustomResponse();
		customResponse.setHtml(fileUtils.getFileContents("home.html"));
		customResponse.setEvents(fileUtils.getFileContents("home/events.json"));
		customResponse.setElements(fileUtils
				.getFileContents("home/elements.json"));
		ObjectWriter ow = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(customResponse);
		return customResponse;

	}

	/**
	 * Redirects to the login page.
	 *
	 * @return the login
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws URISyntaxException the uRI syntax exception
	 */
	@RequestMapping(value = { "/login" })
	public @ResponseBody
	CustomResponse getLogin() throws IOException, URISyntaxException {

		CustomResponse customResponse = new CustomResponse();
		customResponse.setHtml(fileUtils.getFileContents("login.html"));
		customResponse
				.setEvents(fileUtils.getFileContents("login/events.json"));
		ObjectWriter ow = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(customResponse);
		return customResponse;

	}

	/**
	 * Gets the test.
	 *
	 * @return the test
	 */
	@RequestMapping(value = { "/test" })
	public String getTest() {
		return "Test";
	}
}
