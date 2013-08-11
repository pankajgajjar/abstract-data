package com.cs.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.cs.data.core.GenericDomain;
import com.cs.model.ContentObject;

public interface IService {

	public String getAll() throws IOException, URISyntaxException;

	public String create(ContentObject domainObject);

	public List<ContentObject> getAllBy(String structure);

	void move(ContentObject chapter, String path);

	void delete(ContentObject chapter, String path);

}