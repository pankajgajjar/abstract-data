package com.cs.repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.cache.DimensionGroupCache;
import com.cs.data.core.nosql.mongodb.MongoRepository;
import com.cs.model.DimensionGroup;
import com.cs.model.ContentObject;
import com.cs.utils.FileUtils;

@Component
public class DimensionRepository {

	private FileUtils fileUtils;
	private DimensionGroupCache groupCache;
	private MongoRepository noSqlTemplateforMongo;
	private final String FIELDTOUPDATE = "groupIds";
	private final String TYPE = "type";
	private final String GROUPIDS = "groupIds";

	@Autowired
	public DimensionRepository(FileUtils fileUtils,
			DimensionGroupCache groupCache,
			MongoRepository noSqlTemplateforMongo) {

		this.fileUtils = fileUtils;
		this.groupCache = groupCache;
		this.noSqlTemplateforMongo = noSqlTemplateforMongo;
	}

	public String createDimension(ContentObject dimension) {
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
		int count = 0;
		String[] paths = path.split(",");
		for (String singlePath : paths) {
			noSqlTemplateforMongo.updateById(singlePath, FIELDTOUPDATE,
					groupId, ContentObject.class);
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

	public List<ContentObject> getDimensions() {

		return noSqlTemplateforMongo.findAll(ContentObject.class);
	}

	public List<ContentObject> getDimensionsOfType(String type) {
		// TODO Auto-generated method stub
		return noSqlTemplateforMongo.getObjectsBy(TYPE, type,
				ContentObject.class);
	}

	public List<ContentObject> getDimensionsBy(String type2,
			List<String> groupIds) {
		return noSqlTemplateforMongo.getObjectForAndCriteria(TYPE, type2,
				GROUPIDS, groupIds, ContentObject.class);

	}

}
