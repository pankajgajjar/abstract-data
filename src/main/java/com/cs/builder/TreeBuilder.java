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

			buildTreeForRootNode(dimension, orderedTypes, null);
		}

		return rootNodes;
	}

	protected String[] getTypes(String structure) {
		// TODO Auto-generated method stub
		return structure.split(DELIMETER);
	}

	protected List<DimensionModel> getAllSeparatedTrees(String type) {
		return repository.getDimensionsOfType(type);

	}

	protected void buildTreeForRootNode(DimensionModel root,
			String[] orderTypes,
			List<String> groupIdsRequiredForCurrentIteration) {
		List<String> groupIds = null;
		DimensionModel currentRoot = root;
		if (groupIdsRequiredForCurrentIteration == null) {
			groupIds = currentRoot.getGroupId();
		} else {
			groupIds = intersectGroupIds(currentRoot.getGroupId(),
					groupIdsRequiredForCurrentIteration);

		}
		String[] typesOfDimensions = skipFirstOrderType(orderTypes);
		if (typesOfDimensions.length <= 0)
			return;
		List<DimensionModel> childrenOfCurrentLevel = getAllChildrenOfCurrentRoot(
				groupIds, typesOfDimensions[0]);

		System.out.println(groupIds + "====" + typesOfDimensions[0]);

		currentRoot.setChildren(childrenOfCurrentLevel);
		for (DimensionModel child : childrenOfCurrentLevel) {

			buildTreeForRootNode(child, typesOfDimensions, groupIds);

		}

	}

	protected List<DimensionModel> getAllChildrenOfCurrentRoot(
			List<String> groupIds, String type) {
		return repository.getDimensionsBy(type, groupIds);
	}

	private String[] skipFirstOrderType(String[] orderTypes) {
		utils = new ArrayUtils();
		orderTypes = utils.skip(orderTypes, 1);
		return orderTypes;
	}

	private List<String> intersectGroupIds(List<String> groupIds,
			List<String> groupIdsRequiredForCurrentIteration) {
		utils = new ArrayUtils();

		return utils
				.intersection(groupIds, groupIdsRequiredForCurrentIteration);

	}

}
