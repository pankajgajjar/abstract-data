package com.cs.integration;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cs.cache.DimensionGroupCache;
import com.cs.controller.DimensionController;
import com.cs.data.core.nosql.mongodb.MongoRepository;
import com.cs.data.core.nosql.redis.RedisRepository;
import com.cs.model.DimensionGroup;
import com.cs.model.DimensionModel;
import com.cs.repository.DimensionRepository;
import com.cs.service.DimensionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class DimensionIntegrationTests {

	@Autowired
	private MongoRepository noSqlTemplateForMongo;

	List<DimensionModel> models = new ArrayList<DimensionModel>();

	private DimensionGroupCache cache;
	private DimensionRepository dimensionRepository;

	private DimensionService dimensionService;

	@Autowired
	private RedisRepository noSqlTemplateForRedis;

	@Before
	public void setUp() {

		DimensionModel cp01 = new DimensionModel("cp01", "Campaign", "cp01",
				"-1");
		DimensionModel mp01 = new DimensionModel("mp01", "MasterPublication",
				"mp01", "cp01");
		DimensionModel mp012 = new DimensionModel("mp012", "MasterPublication",
				"mp012", "cp01");
		DimensionModel cp02 = new DimensionModel("cp02", "Campaign", "cp02",
				"-1");
		DimensionModel mp02 = new DimensionModel("mp02", "MasterPublication",
				"mp02", "cp02");
		DimensionModel pg02 = new DimensionModel("pg02", "PublicationGroup",
				"pg02", "cp02,mp02");
		DimensionModel p02 = new DimensionModel("p02", "Publication", "p02",
				"cp02,mp02,pg02");
		DimensionModel cp03 = new DimensionModel("cp03", "Campaign", "cp03",
				"-1");
		DimensionModel mp03 = new DimensionModel("mp03", "MasterPublication",
				"mp03", "cp03");
		DimensionModel pg03 = new DimensionModel("pg03", "PublicationGroup",
				"pg03", "cp03,mp03");
		DimensionModel p03 = new DimensionModel("p03", "Publication", "p03",
				"cp03,mp03,pg03");
		DimensionModel cp04 = new DimensionModel("cp04", "Campaign", "cp04",
				"-1");
		DimensionModel mp04 = new DimensionModel("mp04", "MasterPublication",
				"mp04", "cp04");

		models.add(cp01);
		models.add(mp01);
		models.add(mp012);
		models.add(cp02);
		models.add(mp02);
		models.add(pg02);
		models.add(p02);
		models.add(cp03);
		models.add(mp03);
		models.add(pg03);
		models.add(p03);
		models.add(cp04);
		models.add(mp04);

	}

	@Test
	public void itShouldCreateMultipleDimensionGroupsForGivenModels() {

		cache = new DimensionGroupCache(noSqlTemplateForRedis);
		for (DimensionModel dimension : models) {
			dimensionRepository = new DimensionRepository(null, cache,
					noSqlTemplateForMongo);
			dimensionRepository.createDimension(dimension);

		}

		System.out.println(noSqlTemplateForRedis.findAllKeys("*"));

		System.out.println(noSqlTemplateForMongo.findAll(DimensionGroup.class));
	}

}
