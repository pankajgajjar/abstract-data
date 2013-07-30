package com.cs.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.model.CustomResponse;
import com.cs.utils.FileUtils;

@Controller
public class Client {

	private FileUtils fileUtils;

	@Autowired
	public Client(FileUtils fileUtils) {
		// TODO Auto-generated constructor stub
		this.fileUtils = fileUtils;
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET, produces = "application/json")
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

	@RequestMapping(value = { "/test" })
	public String getTest() {
		return "Test";
	}
}
