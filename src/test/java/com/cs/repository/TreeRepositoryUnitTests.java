package com.cs.repository;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cs.data.core.GenericDomain;
import com.cs.data.core.IRepository;
import com.cs.data.core.nosql.mongodb.MongoRepository;
import com.cs.data.core.nosql.redis.RedisRepository;
import com.cs.model.Tree;
import com.cs.model.TreeNode;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TreeRepositoryUnitTests {

	private TreeRepository treeRepository;

	@Mock
	private RedisRepository noSqlTemplateForRedis;

	private Tree tree;

	private TreeNode treeNode;

	@Before
	public void setUp() {
		treeRepository = new TreeRepository(noSqlTemplateForRedis);
	}

	@Test
	public void itShouldCreateAJsonTreeForGivenJsonArray() {

		// given
		JSONArray array = new JSONArray();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", "110");
		jsonObject.put("name", "c01");
		jsonObject.put("children", new JSONArray());
		array.add(jsonObject);
		Tree tree = new Tree();
		tree.setId("11");

		tree.setTreeData(array);

		String expectedResult = "result";
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
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id", "111");
		jsonObject.put("name", "mp01");
		jsonObject.put("children", new JSONArray());
		
		Tree tree=treeRepository.getTree("110", "TREE");
		// when
		treeRepository.addChild(jsonObject,"10",tree);

	}

	@Test
	public void itShouldReturnTree() {
		// given

		Tree result = new Tree();
		// when

		when(noSqlTemplateForRedis.getObjectByKey("10", "TREE", Tree.class))
				.thenReturn(result);
		Tree actualtree = treeRepository.getTree("10", "TREE");

		// then

		verify(noSqlTemplateForRedis).getObjectByKey("10", "TREE", Tree.class);
		assertThat(actualtree).isEqualTo(result);
	}
}
