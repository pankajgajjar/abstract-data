package com.cs.repository;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cs.data.core.IRepository;
import com.cs.data.core.nosql.mongodb.MongoRepository;
import com.cs.data.core.nosql.redis.RedisRepository;
import com.cs.model.Tree;
import com.cs.model.TreeNode;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TreeRepositoryUnitTests {

	private TreeRepository treeRepository;

	@Mock
	private RedisRepository noSqlTemplateForRedis;

	private Tree tree;

	private TreeNode treeNode;
	
	@Test
	public void itShouldCreateAJsonTreeForGivenJsonArray() {

		// given

		String id = "10";
		JSONObject inputJson = new JSONObject();
		JSONArray children = new JSONArray();
		inputJson.put("Name", "Folder");
		inputJson.put("children", children);
		treeRepository = new TreeRepository(noSqlTemplateForRedis);
		Tree actual = null;
		tree = new Tree();
		tree.setId(id);
		tree.setTreeData(inputJson);
		String expectedResult="true";

		// when

		when(noSqlTemplateForRedis.insert(tree)).thenReturn(expectedResult);
		String actualResult = treeRepository.createTree(tree);
		

		// then
		
		verify(noSqlTemplateForRedis).insert(tree);
		assertThat(actualResult).isEqualTo(expectedResult);
		
	}

	@Test
	public void itShouldAddAnNewElement() {

		// given
		int parentId;
		String nodeName = "Compaign";
		treeRepository = new TreeRepository(noSqlTemplateForRedis);
		JSONObject childToAdd = new JSONObject();
		treeNode = new TreeNode();
		treeNode.setChilldren(new JSONArray());
		treeNode.setId("101");
		treeNode.setParentId("101");
		// when
		treeRepository.addChild(treeNode);

	}
}
