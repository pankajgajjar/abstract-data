package com.cs.utils;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Mockito.*;
import static org.fest.assertions.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class FileUtilsUnitTests {
	private FileUtils fileUtils;

	@Test
	public void itShouldReadTheJsonFile() throws IOException, URISyntaxException {
		// given

		String filePath = "home/elements.json";
		// when

		fileUtils = new FileUtils();
		String actualContents = fileUtils.getFileContents(filePath);
		// then
		assertThat(actualContents).contains("id");

	}

}
