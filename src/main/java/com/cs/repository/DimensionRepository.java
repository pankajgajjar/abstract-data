package com.cs.repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	@Autowired
	public DimensionRepository(FileUtils fileUtils,
			DimensionGroupCache groupCache,
			MongoRepository noSqlTemplateforMongo) {

		this.fileUtils = fileUtils;
		this.groupCache = groupCache;
		this.noSqlTemplateforMongo = noSqlTemplateforMongo;
	}

	public String createDimension(DimensionModel dimension) {
		List<DimensionModel> dimensions;

		DimensionGroup dimensionGroup;
		String groupId = getDimensionGroupId(dimension);
		if (groupId == null) {

			dimensionGroup = new DimensionGroup();
			dimensionGroup.setId(new Date().toString());
			dimensions = new ArrayList<DimensionModel>();
			dimensions.add(dimension);
			dimensionGroup.setDimensions(dimensions);
			System.out.println(dimensionGroup);
			noSqlTemplateforMongo.save(dimensionGroup);
			groupCache.addNewGroup(dimension, dimensionGroup.getId());

		} else {
			dimensionGroup = getDimensionGroup(groupId);
			dimensions = dimensionGroup.getDimensions();
			dimensions.add(dimension);
			dimensionGroup.setDimensions(dimensions);
			System.out.println(dimensionGroup);
			noSqlTemplateforMongo.save(dimensionGroup);
			groupCache.updateCache(dimension, groupId);
		}
		return dimension.getId();
	}

	private DimensionGroup getDimensionGroup(String groupId) {
		// TODO Auto-generated method stub
		return noSqlTemplateforMongo.find(groupId, DimensionGroup.class);
	}

	private String getDimensionGroupId(DimensionModel dimension) {

		return groupCache.getDimensionGroupIdFor(dimension);
	}

	public String getAllDimensions() throws IOException, URISyntaxException {
		// TODO Auto-generated method stub
		return fileUtils.getFileContents("dimensions.json");
	}

}
