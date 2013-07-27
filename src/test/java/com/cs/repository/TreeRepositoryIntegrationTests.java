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
		JSONArray array = new JSONArray();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", "11");
		jsonObject.put("name", "c01");
		jsonObject.put("children", new JSONArray());
		array.add(jsonObject);
		Tree tree = new Tree();
		tree.setId("1011");

		tree.setTreeData(array);

		// when

		String actualResult = treeRepository.createTree(tree);

		// then

		System.out.println(actualResult);
		assertThat(actualResult).isNotNull();

	}

	@Test
	public void itShouldAddAnNewElement() {

		// given
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", "11");
		jsonObject.put("name", "mp01");
		jsonObject.put("children", new JSONArray());

		Tree tree = treeRepository.getTree("1011", "TREE");
		// when
		treeRepository.addChild(jsonObject, "11", tree);

	}

	@Test
	public void itShouldReturnTree() {
		// given

		Tree result = new Tree();
		// when

		Tree actualtree = treeRepository.getTree("10", "TREE");

		// then
		System.out.println(actualtree);
		assertThat(actualtree).isNotNull();
	}

	@Test
	public void itShouldAddChildToExistingTree() {

		// given
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id", "11");
		jsonObject.put("name", "mp01");
		jsonObject.put("children", new JSONArray());
		Tree tree=treeRepository.getTree("10", "TREE");
		// when
		treeRepository.addChild(jsonObject,"10",tree);
		
		// then
	}
}
