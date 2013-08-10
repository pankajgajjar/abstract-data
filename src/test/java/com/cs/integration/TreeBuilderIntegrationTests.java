package com.cs.integration;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cs.builder.TreeBuilder;
import com.cs.cache.DimensionGroupCache;
import com.cs.data.core.nosql.mongodb.MongoRepository;
import com.cs.data.core.nosql.redis.RedisRepository;
import com.cs.model.ContentObject;
import com.cs.repository.DimensionRepository;
import com.cs.utils.FileUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class TreeBuilderIntegrationTests {

	private TreeBuilder builder;

	private RedisRepository noSqlTemplateforRedis;

	private MongoRepository noSqlTemplateforMongo;

	@Autowired
	private MongoOperations mongoTemplate;

	@Autowired
	private RedisOperations redisTemplate;
	private FileUtils fileUtils;
	private DimensionGroupCache cache;
	private DimensionRepository repository;

	@Test
	public void itShouldReturnTheWholeTree() {
		noSqlTemplateforMongo = new MongoRepository(mongoTemplate);
		noSqlTemplateforRedis = new RedisRepository(redisTemplate);
		fileUtils = new FileUtils();
		cache = new DimensionGroupCache(noSqlTemplateforRedis);
		repository = new DimensionRepository(fileUtils, cache,
				noSqlTemplateforMongo);

		builder = new TreeBuilder(cache, repository);

		List<ContentObject> models = builder
				.buildTree("Campaign-MasterPublication-PublicationGroup-Publication");
		System.out.println(models);

	}

}
