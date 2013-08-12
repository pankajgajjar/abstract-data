package com.cs.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.data.core.nosql.redis.RedisRepository;
import com.cs.model.ContentObject;


/**
 * The Class DimensionGroupCache.
 */
@Component
public class DimensionGroupCache {

	/** The no sql template for redis. */
	private RedisRepository noSqlTemplateForRedis;

	/**
	 * Instantiates a new dimension group cache.
	 *
	 * @param noSqlTemplateForRedis the no sql template for redis
	 */
	@Autowired
	public DimensionGroupCache(RedisRepository noSqlTemplateForRedis) {
		this.noSqlTemplateForRedis = noSqlTemplateForRedis;

	}

	/**
	 * Gets the dimension group id for the given path.
	 *
	 * @param path the path
	 * @return the dimension group id for
	 */
	public String getDimensionGroupIdFor(String path) {
		// TODO Auto-generated method stub
		return noSqlTemplateForRedis.get(path);
	}

	/**
	 * Update cache for the given group taking the given dimension and groupid.
	 *
	 * @param dimension the dimension
	 * @param groupId the group id
	 */
	public void updateCache(ContentObject dimension, String groupId) {

		delete(dimension);
		noSqlTemplateForRedis.set(
				dimension.getPath().concat("," + dimension.getName()), groupId);
	}

	/**
	 * Delete.
	 *
	 * @param dimension the dimension
	 */
	private void delete(ContentObject dimension) {
		// TODO Auto-generated method stub

		noSqlTemplateForRedis.delete(dimension.getPath());

	}

	/**
	 * Adds the new group taking the groupId.
	 *
	 * @param dimension the dimension
	 * @param groupId the group id
	 */
	public void addNewGroup(ContentObject dimension, String groupId) {
		// TODO Auto-generated method stub
		if (dimension.isRoot()) {
			noSqlTemplateForRedis.set(dimension.getName(), groupId);
		} else {
			noSqlTemplateForRedis.set(
					dimension.getPath().concat("," + dimension.getName()),
					groupId);
		}

	}

	/**
	 * Checks if the Given Dimension Exists
	 *
	 * @param path the path
	 * @return true, if successful
	 */
	public boolean ifGroupIdExistsFor(String path) {
		return getDimensionGroupIdFor(path) == null ? false : true;

	}

	/**
	 * Gets the all groups.
	 *
	 * @return the all groups
	 */
	public List<String> getAllGroups() {
		// TODO Auto-generated method stub
		return null;
	}

}
