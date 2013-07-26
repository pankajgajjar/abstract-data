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

@Component
public class FileUtils {

	public String getFileContents(String filePath) throws IOException, URISyntaxException {
		// TODO Auto-generated method stub

		byte[] encoded = Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(filePath).toURI()));
		return Charset.defaultCharset().decode(ByteBuffer.wrap(encoded))
				.toString();

	}

}
