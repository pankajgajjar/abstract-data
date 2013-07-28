package com.cs.repository;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cs.data.core.nosql.NoSqlOperations;
import com.cs.model.Tree;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class TreeRepositoryIntegrationTests {

	private TreeRepository treeRepository;
	@Autowired
	private NoSqlOperations noSqlTemplateForRedis;

	@Before
	public void setUp() {

		treeRepository = new TreeRepository(noSqlTemplateForRedis);
	}

	@Test
	public void itShouldCreateTree() {
		// given
		JSONObject firstElement = new JSONObject();
		firstElement.put("id", "100");
		firstElement.put("name", "c02");
		firstElement.put("children", new JSONArray());

		JSONObject attr = new JSONObject();
		attr.put("attr", firstElement);
		JSONArray treeData = new JSONArray();
		treeData.add(attr);
		Tree tree = new Tree();
		tree.setId("tree01");
		tree.setTreeData(treeData);

		// when

		String actualResult = treeRepository.createTree(tree);

		// then

		assertThat(actualResult).isNotNull();

	}

	@Test
	public void itShouldReturnTree() {
		// given

		Tree result = new Tree();
		// when

		Tree actualtree = treeRepository.getTree("tree01", "TREE");

		// then
		System.out.println(actualtree);
		assertThat(actualtree).isNotNull();
	}

	@Test
	public void itShouldAddChildToExistingRoot() {

		// given
		JSONObject firstElement = new JSONObject();
		firstElement.put("id", "101");
		firstElement.put("name", "mp012");
		firstElement.put("children", new JSONArray());

		JSONObject attr = new JSONObject();
		attr.put("attr", firstElement);
		Tree tree = treeRepository.getTree("tree01", "TREE");
		// when
		treeRepository.addChild(attr, "100", tree);

		// then
	}
	
/*	@Test
	public void itShouldAddChildToExistingNested() {

		// given
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", "12");
		jsonObject.put("name", "publication01");
		jsonObject.put("children", new JSONArray());
		Tree tree = treeRepository.getTree("tree01", "TREE");
		// when
		treeRepository.addChild(jsonObject, "11", tree);

		// then
		
		
		
		
	}
	
	@Test
	public void itShouldAddChildToExistingNestedSecondLevel() {

		// given
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", "13");
		jsonObject.put("name", "page01");
		jsonObject.put("children", new JSONArray());
		Tree tree = treeRepository.getTree("tree01", "TREE");
		// when
		treeRepository.addChild(jsonObject, "12", tree);

		// then
		
		
		
		
	}*/
}
