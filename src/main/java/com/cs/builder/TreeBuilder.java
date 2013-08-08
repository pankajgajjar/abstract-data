package com.cs.builder;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.cache.DimensionGroupCache;
import com.cs.model.DimensionModel;
import com.cs.model.DimensionModelList;
import com.cs.repository.DimensionRepository;
import com.cs.utils.ArrayUtils;

@Component
public class TreeBuilder {

	private DimensionGroupCache cache;
	private DimensionRepository repository;
	private DimensionModelList listOfNotConnectedTrees;
	private ArrayUtils utils;
	private final String DELIMETER = "-";

	@Autowired
	public TreeBuilder(DimensionGroupCache cache, DimensionRepository repository) {
		this.cache = cache;
		this.repository = repository;
	}

	public List<DimensionModel> buildTree(String structure) {
		String[] orderedTypes = getTypes(structure);
		List<DimensionModel> rootNodes = getAllSeparatedTrees(orderedTypes[0]);
		for (DimensionModel dimension : rootNodes) {

			buildTreeForRootNode(dimension, orderedTypes);
		}

		return null;
	}

	protected String[] getTypes(String structure) {
		// TODO Auto-generated method stub
		return structure.split(DELIMETER);
	}

	protected List<DimensionModel> getAllSeparatedTrees(String type) {
		return repository.getDimensionsOfType(type);

	}

	protected void buildTreeForRootNode(DimensionModel root, String[] orderTypes) {

		List<DimensionModel> childrenOfCurrentLevel = null;
		List<String> groupIds = null;
		DimensionModel currentRoot = root;
		String[] typesOfDimensions = skipFirstOrderType(orderTypes);
		groupIds = currentRoot.getGroupId();
		for (String type : typesOfDimensions) {
			childrenOfCurrentLevel = getAllChildrenOfCurrentRoot(groupIds, type);
			currentRoot.setChildren(childrenOfCurrentLevel);
			for (DimensionModel child : childrenOfCurrentLevel) {

			}

		}
	}

	protected List<DimensionModel> getAllChildrenOfCurrentRoot(
			List<String> groupIds, String type) {
		// TODO Auto-generated method stub
		return repository.getDimensionsBy(type, groupIds);
	}

	private String[] skipFirstOrderType(String[] orderTypes) {
		// TODO Auto-generated method stub
		utils = new ArrayUtils();
		orderTypes = utils.skip(orderTypes, 1);
		return orderTypes;
	}

}
