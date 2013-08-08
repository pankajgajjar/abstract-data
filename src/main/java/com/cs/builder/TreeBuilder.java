package com.cs.builder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.cache.DimensionGroupCache;
import com.cs.model.DimensionModel;
import com.cs.model.DimensionModelList;
import com.cs.repository.DimensionRepository;

@Component
public class TreeBuilder {

	private DimensionGroupCache cache;
	private DimensionRepository repository;
	private DimensionModelList listOfNotConnectedTrees;
	private final String DELIMETER = "-";

	@Autowired
	public TreeBuilder(DimensionGroupCache cache, DimensionRepository repository) {
		this.cache = cache;
		this.repository = repository;
	}

	public List<DimensionModel> buildTree(String structure) {
		String[] orderedTypes = getTypes(structure);
		List<DimensionModel> models = getAllSeparatedTrees(orderedTypes[0]);
		
		return null;
	}

	protected String[] getTypes(String structure) {
		// TODO Auto-generated method stub
		return structure.split(DELIMETER);
	}

	protected List<DimensionModel> getAllSeparatedTrees(String type) {
		return repository.getDimensionsOfType(type);

	}

}
