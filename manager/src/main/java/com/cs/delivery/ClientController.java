package com.cs.delivery;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletContext;

import org.json.simple.parser.ParseException;
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

	/** The index. */
	private final String INDEX = "redirect:/engine/core/html/start.html";
	private final String PREFIX = "";

	@Autowired
	private ServletContext context;

	/** The file utils. */
	private FileUtils fileUtils;

	/**
	 * Instantiates a new client controller.
	 * 
	 * @param fileUtils
	 *            the file utils
	 */
	@Autowired
	public ClientController(FileUtils fileUtils) {
		// TODO Auto-generated constructor stub
		this.fileUtils = fileUtils;
	}

	/**
	 * Redirects to the the index page.
	 * 
	 * @return the index page
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String getIndexPage() throws ParseException, IOException,
			URISyntaxException {
		return INDEX;
	}

	/**
	 * Redirects to the Home page.
	 * 
	 * @return the home
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             the uRI syntax exception
	 */
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	CustomResponse getHome() throws IOException, URISyntaxException {
		CustomResponse customResponse = new CustomResponse();
		customResponse.setHtml(fileUtils.getFileContents("home.html"));
		customResponse.setEvents(fileUtils.getFileContents("home/events.json"));
		customResponse.setElements(fileUtils
				.getFileContents("home/elements.json"));
		return customResponse;

	}

	/**
	 * Redirects to the login page.
	 * 
	 * @return the login
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             the uRI syntax exception
	 */
	@RequestMapping(value = { "/login" })
	public @ResponseBody
	CustomResponse getLogin() throws IOException, URISyntaxException {

		CustomResponse customResponse = new CustomResponse();
		customResponse.setHtml(fileUtils.getFileContents("login.html"));
		customResponse
				.setEvents(fileUtils.getFileContents("login/events.json"));
		return customResponse;

	}
	
	@RequestMapping(value = { "/header" })
	public @ResponseBody
	CustomResponse getHeader() throws IOException, URISyntaxException {

		CustomResponse customResponse = new CustomResponse();
		customResponse.setHtml(fileUtils.getFileContents("header.html"));
		return customResponse;

	}
	
	@RequestMapping(value = { "/footer" })
	public @ResponseBody
	CustomResponse getFooter() throws IOException, URISyntaxException {

		CustomResponse customResponse = new CustomResponse();
		customResponse.setHtml(fileUtils.getFileContents("footer.html"));
		return customResponse;

	}

}
