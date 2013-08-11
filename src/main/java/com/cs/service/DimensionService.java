package com.cs.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.builder.TreeBuilder;
import com.cs.model.ContentObject;
import com.cs.repository.DimensionRepository;

@Component
public class DimensionService implements IService {

	private TreeBuilder treeBuilder;
	private DimensionRepository dimensionRepository;

	@Autowired
	public DimensionService(DimensionRepository dimensionRepository,
			TreeBuilder treeBuilder) {

		this.dimensionRepository = dimensionRepository;
		this.treeBuilder = treeBuilder;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.service.IService#getAll()
	 */
	@Override
	public String getAll() throws IOException, URISyntaxException {
		// TODO Auto-generated method stub

		return dimensionRepository.getAllDimensions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.service.IService#create(com.cs.model.DimensionModel)
	 */
	@Override
	public String create(ContentObject dimension) {

		return dimensionRepository.createDimension(dimension);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.service.IService#getAllBy(java.lang.String)
	 */
	@Override
	public List<ContentObject> getAllBy(String structure) {
		// TODO Auto-generated method stub

		return treeBuilder.buildTree(structure);

	}

	@Override
	public void move(ContentObject chapter, String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(ContentObject chapter, String path) {
		// TODO Auto-generated method stub

	}

}
