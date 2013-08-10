package com.cs.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.cs.model.DimensionModel;

public interface IService {

	public String getAll() throws IOException, URISyntaxException;

	public <T> String create(T domainObject);

	public List<DimensionModel> getAllBy(String structure);

}