package com.cs.service;

import java.io.IOException;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.model.DimensionModel;
import com.cs.repository.DimensionRepository;

@Component
public class DimensionService {

	private DimensionRepository dimensionRepository;

	@Autowired
	public DimensionService(DimensionRepository dimensionRepository) {

		this.dimensionRepository = dimensionRepository;

	}

	public String getAllDimensions() throws IOException, URISyntaxException {
		// TODO Auto-generated method stub

		return dimensionRepository.getAllDimensions();
	}

	public String createDimension(DimensionModel dimension) {
		
		return dimensionRepository.createDimension(dimension);
	}

}
