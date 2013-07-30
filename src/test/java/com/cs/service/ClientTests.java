package com.cs.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.model.CustomResponse;
import com.cs.utils.FileUtils;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientTests {

	private Client client;

	@Mock
	private FileUtils fileUtils;

	@Before
	public void setUp() {

		client = new Client(fileUtils);
	}

	@Test
	public void itShouldReturnHomeHTMLfile() throws IOException,
			URISyntaxException {
		// given

		String contents="result";
		JSONObject contentObject = new JSONObject();
		contentObject.put("html", "test");
		when(fileUtils.getFileContents("home.html")).thenReturn(contents);
		// when
		CustomResponse actualContents = client.getHome();

		// then

		verify(fileUtils).getFileContents("home.html");
		assertThat(contents).isEqualTo(contents);

	}

}
