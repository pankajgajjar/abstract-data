package com.cs.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.builder.TreeBuilder;
import com.cs.model.ContentObject;
import com.cs.repository.DimensionRepository;

/**
 * The Class DimensionService.
 */
@Component
public class DimensionService implements IService {

	/** The tree builder. */
	private TreeBuilder treeBuilder;
	
	/** The dimension repository. */
	private DimensionRepository dimensionRepository;

	/**
	 * Instantiates a new dimension service.
	 *
	 * @param dimensionRepository the dimension repository
	 * @param treeBuilder the tree builder
	 */
	@Autowired
	public DimensionService(DimensionRepository dimensionRepository,
			TreeBuilder treeBuilder) {

		this.dimensionRepository = dimensionRepository;
		this.treeBuilder = treeBuilder;

	}

	/*
	 * Get all dimension.
	 * 
	 * @see com.cs.service.IService#getAll()
	 */
	@Override
	public String getAll() throws IOException, URISyntaxException {
		// TODO Auto-generated method stub

		return dimensionRepository.getAllDimensions();
	}

	/*
	 * Creates new dimension.
	 * 
	 * @see com.cs.service.IService#create(com.cs.model.DimensionModel)
	 */
	@Override
	public String create(ContentObject dimension) {

		return dimensionRepository.createDimension(dimension);
	}

	/*
	 * Get all chapters for given structure.
	 * 
	 * @see com.cs.service.IService#getAllBy(java.lang.String)
	 */
	@Override
	public List<ContentObject> getAllBy(String structure) {
		// TODO Auto-generated method stub

		return treeBuilder.buildTree(structure);

	}

	/* (non-Javadoc)
	 * @see com.cs.service.IService#move(com.cs.model.ContentObject, java.lang.String)
	 */
	@Override
	public void move(ContentObject chapter, String path) {
		// TODO Auto-generated method stub

	}

	/* Deletes the dimension.
	 * @see com.cs.service.IService#delete(com.cs.model.ContentObject, java.lang.String)
	 */
	@Override
	public void delete(ContentObject chapter, String path) {
		// TODO Auto-generated method stub

	}

}
