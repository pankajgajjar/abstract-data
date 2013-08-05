package com.cs.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;

import com.cs.model.DimensionGroup;
import com.cs.model.DimensionModel;
import com.cs.repository.DimensionRepository;
import com.cs.utils.FileUtils;

public class DimensionService {

	private FileUtils fileUtils;

	private DimensionRepository dimensionRepository;

	@Autowired
	public DimensionService(FileUtils fileUtils,
			DimensionRepository dimensionRepository) {

		this.fileUtils = fileUtils;
		this.dimensionRepository = dimensionRepository;

	}

	public String getAllDimensions() throws IOException, URISyntaxException {
		// TODO Auto-generated method stub

		return fileUtils.getFileContents("dimensions.json");
	}

	public String createDimension(DimensionModel dimension) {

		return dimensionRepository.createDimension(dimension);
	}

}
