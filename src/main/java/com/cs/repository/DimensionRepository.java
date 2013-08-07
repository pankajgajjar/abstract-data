package com.cs.repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.cache.DimensionGroupCache;
import com.cs.data.core.IRepository;
import com.cs.data.core.nosql.NoSqlOperations;
import com.cs.data.core.nosql.mongodb.MongoRepository;
import com.cs.model.DimensionGroup;
import com.cs.model.DimensionModel;
import com.cs.utils.FileUtils;
import com.google.common.cache.Cache;

@Component
public class DimensionRepository {

	private FileUtils fileUtils;
	private DimensionGroupCache groupCache;
	private MongoRepository noSqlTemplateforMongo;
	private final String FIELDTOUPDATE = "groupIds";

	@Autowired
	public DimensionRepository(FileUtils fileUtils,
			DimensionGroupCache groupCache,
			MongoRepository noSqlTemplateforMongo) {

		this.fileUtils = fileUtils;
		this.groupCache = groupCache;
		this.noSqlTemplateforMongo = noSqlTemplateforMongo;
	}

	public String createDimension(DimensionModel dimension) {
		String groupId = getDimensionGroupId(dimension.getPath());
		if (groupCache.ifGroupIdExistsFor(dimension.getPath())) {
			dimension.addToGroupId(groupId);
			dimension.setChildren(null);
			noSqlTemplateforMongo.save(dimension);
			groupCache.updateCache(dimension, groupId);
		} else {
			groupId = UUID.randomUUID().toString();
			groupCache.addNewGroup(dimension, groupId);

			updateGroupIdForAllAncestor(dimension.getPath(), groupId);
			dimension.addToGroupId(groupId);
			dimension.setChildren(null);
			noSqlTemplateforMongo.save(dimension);
		}

		return dimension.getId();
	}

	private void updateGroupIdForAllAncestor(String path, String groupId) {

		String[] paths = path.split(",");

		for (String singlePath : paths) {
			noSqlTemplateforMongo.updateById(singlePath, FIELDTOUPDATE,
					groupId, DimensionModel.class);
		}

	}

	private DimensionGroup getDimensionGroup(String groupId) {
		// TODO Auto-generated method stub
		return noSqlTemplateforMongo.find(groupId, DimensionGroup.class);
	}

	private String getDimensionGroupId(String path) {

		return groupCache.getDimensionGroupIdFor(path);
	}

	public String getAllDimensions() throws IOException, URISyntaxException {
		// TODO Auto-generated method stub
		return fileUtils.getFileContents("dimensions.json");
	}

}
