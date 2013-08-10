package com.cs.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.data.core.nosql.redis.RedisRepository;
import com.cs.model.ContentObject;

@Component
public class DimensionGroupCache {

	private RedisRepository noSqlTemplateForRedis;

	@Autowired
	public DimensionGroupCache(RedisRepository noSqlTemplateForRedis) {
		this.noSqlTemplateForRedis = noSqlTemplateForRedis;

	}

	public String getDimensionGroupIdFor(String path) {
		// TODO Auto-generated method stub
		return noSqlTemplateForRedis.get(path);
	}

	public void updateCache(ContentObject dimension, String groupId) {

		delete(dimension);
		noSqlTemplateForRedis.set(
				dimension.getPath().concat("," + dimension.getName()), groupId);
	}

	private void delete(ContentObject dimension) {
		// TODO Auto-generated method stub

		noSqlTemplateForRedis.delete(dimension.getPath());

	}

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

	public boolean ifGroupIdExistsFor(String path) {
		return getDimensionGroupIdFor(path) == null ? false : true;

	}

	public List<String> getAllGroups() {
		// TODO Auto-generated method stub
		return null;
	}

}
