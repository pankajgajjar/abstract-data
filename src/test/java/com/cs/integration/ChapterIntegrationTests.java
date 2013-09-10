package com.cs.integration;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cs.cache.ViewStructureCache;
import com.cs.data.core.nosql.InMemoryNoSqlRepository;
import com.cs.data.core.nosql.mongodb.MongoRepository;
import com.cs.model.ContentObject;
import com.cs.repository.ChapterRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class ChapterIntegrationTests {

	@Autowired
	private MongoRepository noSqlRepository;

	List<ContentObject> models = new ArrayList<ContentObject>();

	private ViewStructureCache cache;
	private ChapterRepository chapterRepository;

	@Autowired
	private InMemoryNoSqlRepository inMemoryNosqlRepository;

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
		cache = new ViewStructureCache(inMemoryNosqlRepository);

		models.add(page01);
		models.add(chapter01);
		models.add(chapter02);
		models.add(page02);

	}

	@Test
	public void itShouldCreateMultipleDimensionGroupsForGivenModels() {

		cache = new ViewStructureCache(inMemoryNosqlRepository);
		for (ContentObject dimension : models) {
			chapterRepository = new ChapterRepository(noSqlRepository, cache);
			String result = chapterRepository.save(dimension);

			assertThat(result).isNotNull();
			assertThat(result).isEqualTo("inserted");

		}

	}

	@Test
	public void itShouldRemoveAnObjectFromPublication() {
		chapterRepository = new ChapterRepository(noSqlRepository, cache);

		String result = chapterRepository.delete(page01, "mp02,pg02,c02,p02");

		assertThat(result).isEqualTo(page01.getId());

	}

}
