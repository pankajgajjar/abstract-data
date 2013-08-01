package com.cs.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import com.cs.model.DimensionGroup;

@Component
public class DimensionGroupRepository {

	private MongoOperations mongoTemplate;

	@Autowired
	public DimensionGroupRepository(MongoOperations mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public void save(DimensionGroup objectToSave) {

		mongoTemplate.insert(objectToSave);

	}

	public List<DimensionGroup> findBy(String dimensionType) {

		List<DimensionGroup> groups = mongoTemplate
				.findAll(DimensionGroup.class);
		return groups;

	}

	public List<DimensionGroup> findBy() {

		return null;

	}

}
