package com.cs.builder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.cache.DimensionGroupCache;
import com.cs.model.MultiDimensionalObject;
import com.cs.repository.DimensionRepository;
import com.cs.utils.ArrayUtils;

/**
 * The Class TreeBuilder.
 */
@Component
public class TreeBuilder {

	/** The cache. */
	private DimensionGroupCache cache;

	/** The repository. */
	private DimensionRepository repository;

	/** The utils. */
	private ArrayUtils utils;

	/** The delimeter. */
	private final String DELIMETER = "-";

	/**
	 * Instantiates a new tree builder.
	 * 
	 * @param cache
	 *            the cache
	 * @param repository
	 *            the repository
	 */
	@Autowired
	public TreeBuilder(DimensionGroupCache cache, DimensionRepository repository) {
		this.cache = cache;
		this.repository = repository;
	}

	/**
	 * Builds the tree.
	 * 
	 * @param structure
	 *            the structure
	 * @return the list
	 */
	public List<MultiDimensionalObject> buildTree(String structure) {
		String[] orderedTypes = getTypes(structure);
		List<MultiDimensionalObject> rootNodes = getAllSeparatedTrees(orderedTypes[0]);
		for (MultiDimensionalObject dimension : rootNodes) {

			dimension.setPath("-1");
			buildTreeForRootNode(dimension, orderedTypes, null);
		}

		return rootNodes;
	}

	/**
	 * Gets the types.
	 * 
	 * @param structure
	 *            the structure
	 * @return the types
	 */
	protected String[] getTypes(String structure) {
		// TODO Auto-generated method stub
		return structure.split(DELIMETER);
	}

	/**
	 * Gets the all separated trees.
	 * 
	 * @param type
	 *            the type
	 * @return the all separated trees
	 */
	protected List<MultiDimensionalObject> getAllSeparatedTrees(String type) {
		return repository.getDimensionsOfType(type);

	}

	/**
	 * Builds the tree for the given structure provided.
	 * 
	 * @param root
	 *            the root
	 * @param orderTypes
	 *            the order types
	 * @param groupIdsRequiredForCurrentIteration
	 *            the group ids required for current iteration
	 */
	protected void buildTreeForRootNode(MultiDimensionalObject root,
			String[] orderTypes,
			List<String> groupIdsRequiredForCurrentIteration) {
		List<String> groupIds = null;
		MultiDimensionalObject currentRoot = root;
		if (groupIdsRequiredForCurrentIteration == null) {
			groupIds = currentRoot.getGroupId();
		} else {
			groupIds = intersectGroupIds(currentRoot.getGroupId(),
					groupIdsRequiredForCurrentIteration);

		}
		String[] typesOfDimensions = skipFirstOrderType(orderTypes);
		if (typesOfDimensions.length <= 0)
			return;
		List<MultiDimensionalObject> childrenOfCurrentLevel = getAllChildrenOfCurrentRoot(
				groupIds, typesOfDimensions[0]);

		currentRoot.setChildren(childrenOfCurrentLevel);
		for (MultiDimensionalObject child : childrenOfCurrentLevel) {
			child.setPath(removeMinusOne(currentRoot.getPath()) + ","
					+ currentRoot.getName());

			buildTreeForRootNode(child, typesOfDimensions, groupIds);

		}

	}

	/**
	 * Gets the all children of current root.
	 * 
	 * @param groupIds
	 *            the group ids
	 * @param type
	 *            the type
	 * @return the all children of current root
	 */
	protected List<MultiDimensionalObject> getAllChildrenOfCurrentRoot(
			List<String> groupIds, String type) {
		return repository.getDimensionsBy(type, groupIds);
	}

	/**
	 * Skip first order type.
	 * 
	 * @param orderTypes
	 *            the order types
	 * @return the string[]
	 */
	private String[] skipFirstOrderType(String[] orderTypes) {
		utils = new ArrayUtils();
		orderTypes = utils.skip(orderTypes, 1);
		return orderTypes;
	}

	protected String removeMinusOne(String path) {
		path = path.startsWith("-1") && path.length() > 2 ? path.substring(3)
				: path;

		return path;
	}

	/**
	 * Intersect group ids.
	 * 
	 * @param groupIds
	 *            the group ids
	 * @param groupIdsRequiredForCurrentIteration
	 *            the group ids required for current iteration
	 * @return the list
	 */
	private List<String> intersectGroupIds(List<String> groupIds,
			List<String> groupIdsRequiredForCurrentIteration) {
		utils = new ArrayUtils();

		return utils
				.intersection(groupIds, groupIdsRequiredForCurrentIteration);

	}

}
