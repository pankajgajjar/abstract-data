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
import com.cs.cache.ViewStructureCache;
import com.cs.controller.NodeController;
import com.cs.data.core.nosql.mongodb.MongoRepository;
import com.cs.data.core.nosql.redis.RedisRepository;
import com.cs.model.DimensionGroup;
import com.cs.model.ContentObject;
import com.cs.repository.ChapterRepository;
import com.cs.repository.DimensionRepository;
import com.cs.service.IService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class ChapterIntegrationTests {

	@Autowired
	private MongoRepository noSqlTemplateForMongo;

	List<ContentObject> models = new ArrayList<ContentObject>();

	private ViewStructureCache cache;
	private ChapterRepository chapterRepository;

	@Autowired
	private RedisRepository noSqlTemplateForRedis;

	ContentObject page01;

	@Before
	public void setUp() {

		page01 = new ContentObject("page01", "page", "mp02,pg02,c02,p02",
				"false");
		ContentObject chapter01 = new ContentObject("chapter01", "page",
				"mp02,pg02,c02,p02", "false");
		ContentObject chapter02 = new ContentObject("chapter02", "page",
				"mp02,pg02,c02,p02,chapter01", "false");
		ContentObject page02 = new ContentObject("page02", "page",
				"mp02,pg02,c02,p02,chapter01,chapter02", "false");
		cache = new ViewStructureCache(noSqlTemplateForRedis);

		models.add(page01);
		models.add(chapter01);
		models.add(chapter02);
		models.add(page02);

	}

	/*
	 * @Test public void itShouldCreateMultipleDimensionGroupsForGivenModels() {
	 * 
	 * cache = new ViewStructureCache(noSqlTemplateForRedis); for (ContentObject
	 * dimension : models) { chapterRepository = new
	 * ChapterRepository(noSqlTemplateForMongo, cache);
	 * chapterRepository.save(dimension);
	 * 
	 * }
	 * 
	 * System.out.println(noSqlTemplateForRedis.findAllKeys("*"));
	 * 
	 * System.out.println(noSqlTemplateForMongo.findAll(ContentObject.class)); }
	 */

	@Test
	public void itShouldRemoveAnObjectFromPublication() {
		chapterRepository = new ChapterRepository(noSqlTemplateForMongo, cache);

		chapterRepository.delete(page01, "mp02,pg02,c02,p02");

	}

}
