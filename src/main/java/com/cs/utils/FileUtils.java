package com.cs.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

/**
 * The Class FileUtils.
 */

public class FileUtils {

	/**
	 * Gets the file contents.
	 *
	 * @param filePath the file path
	 * @return the file contents
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws URISyntaxException the uRI syntax exception
	 */
	public String getFileContents(String filePath) throws IOException,
			URISyntaxException {
		// TODO Auto-generated method stub

		System.out.println(filePath);
		byte[] encoded = Files.readAllBytes(Paths.get(getClass()
				.getClassLoader().getResource(filePath).toURI()));
		return Charset.defaultCharset().decode(ByteBuffer.wrap(encoded))
				.toString();

	}

}
