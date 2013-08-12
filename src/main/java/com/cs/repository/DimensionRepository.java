package com.cs.repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.cache.DimensionGroupCache;
import com.cs.data.core.nosql.NoSqlRepository;
import com.cs.model.DimensionGroup;
import com.cs.model.ContentObject;
import com.cs.utils.FileUtils;

/**
 * The Class DimensionRepository.
 */
@Component
public class DimensionRepository {

	/** The file utils. */
	private FileUtils fileUtils;

	/** The group cache. */
	private DimensionGroupCache groupCache;

	/** The no sql templatefor mongo. */
	private NoSqlRepository noSqlRepository;

	/** The fieldtoupdate. */
	private final String FIELDTOUPDATE = "groupIds";

	/** The type. */
	private final String TYPE = "type";

	/** The groupids. */
	private final String GROUPIDS = "groupIds";

	/**
	 * Instantiates a new dimension repository.
	 * 
	 * @param fileUtils
	 *            the file utils
	 * @param groupCache
	 *            the group cache
	 * @param noSqlTemplateforMongo
	 *            the no sql templatefor mongo
	 */
	@Autowired
	public DimensionRepository(FileUtils fileUtils,
			DimensionGroupCache groupCache, NoSqlRepository noSqlRepository) {

		this.fileUtils = fileUtils;
		this.groupCache = groupCache;
		this.noSqlRepository = noSqlRepository;
	}

	/**
	 * Creates the dimension.
	 * 
	 * @param dimension
	 *            the dimension
	 * @return the string
	 */
	public String createDimension(ContentObject dimension) {
		String groupId = getDimensionGroupId(dimension.getPath());
		if (groupCache.ifGroupIdExistsFor(dimension.getPath())) {
			dimension.addToGroupId(groupId);
			dimension.setChildren(null);
			noSqlRepository.save(dimension);
			groupCache.updateCache(dimension, groupId);
		} else {
			groupId = UUID.randomUUID().toString();
			groupCache.addNewGroup(dimension, groupId);

			updateGroupIdForAllAncestor(dimension.getPath(), groupId);
			dimension.addToGroupId(groupId);
			dimension.setChildren(null);
			noSqlRepository.save(dimension);
		}

		return dimension.getId();
	}

	/**
	 * Update group id for all ancestor.
	 * 
	 * @param path
	 *            the path
	 * @param groupId
	 *            the group id
	 */
	private void updateGroupIdForAllAncestor(String path, String groupId) {
		int count = 0;
		String[] paths = path.split(",");
		for (String singlePath : paths) {
			noSqlRepository.updateById(singlePath, FIELDTOUPDATE, groupId,
					ContentObject.class);
		}

	}

	/**
	 * Gets the dimension group.
	 * 
	 * @param groupId
	 *            the group id
	 * @return the dimension group
	 */
	private DimensionGroup getDimensionGroup(String groupId) {
		// TODO Auto-generated method stub
		return noSqlRepository.find(groupId, DimensionGroup.class);
	}

	/**
	 * Gets the dimension group id.
	 * 
	 * @param path
	 *            the path
	 * @return the dimension group id
	 */
	private String getDimensionGroupId(String path) {

		return groupCache.getDimensionGroupIdFor(path);
	}

	/**
	 * Gets the all dimensions.
	 * 
	 * @return the all dimensions
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             the uRI syntax exception
	 */
	public String getAllDimensions() throws IOException, URISyntaxException {
		// TODO Auto-generated method stub
		return fileUtils.getFileContents("dimensions.json");
	}

	/**
	 * Gets the dimensions.
	 * 
	 * @return the dimensions
	 */
	public List<ContentObject> getDimensions() {

		return noSqlRepository.findAll(ContentObject.class);
	}

	/**
	 * Gets the dimensions of type.
	 * 
	 * @param type
	 *            the type
	 * @return the dimensions of type
	 */
	public List<ContentObject> getDimensionsOfType(String type) {
		// TODO Auto-generated method stub
		return noSqlRepository.getObjectsBy(TYPE, type, ContentObject.class);
	}

	/**
	 * Gets the dimensions by type.
	 * 
	 * @param type2
	 *            the type2
	 * @param groupIds
	 *            the group ids
	 * @return the dimensions by
	 */
	public List<ContentObject> getDimensionsBy(String type2,
			List<String> groupIds) {
		return noSqlRepository.getObjectForAndCriteria(TYPE, type2, GROUPIDS,
				groupIds, ContentObject.class);

	}

}
