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
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class TreeRepositoryUnitTests {

	private TreeRepository treeRepository;

	@Autowired
	private IRepository noSqlTemplateForRedis;


	private Tree tree;
	
	private TreeNode treeNode;

	@Test
	public void itShouldCreateAJsonTreeForGivenJsonArray() {

		// given
		
		String id = "10";
		JSONObject inputJson = new JSONObject();
		JSONArray children=new JSONArray();
		inputJson.put("Name", "Folder");
		inputJson.put("children", children);
		treeRepository = new TreeRepository(noSqlTemplateForRedis);
		Tree actual = null;
		tree = new Tree();
		tree.setId(id);
		tree.setTreeData(inputJson);

		// when

		int rootId = treeRepository.createTree(tree);
		actual = noSqlTemplateForRedis.getObjectByKey(tree, Tree.class);

		// then
		System.out.println(actual);
		assertThat(actual).isNotNull();
		assertThat(actual.getTreeData().get("Name")).isEqualTo(inputJson.get("Name"));
	}

	@Test
	public void itShouldAddAnNewElement(){
		
		//given
		int parentId;
		String nodeName="Compaign";
		treeRepository=new TreeRepository(noSqlTemplateForRedis);
		JSONObject childToAdd=new JSONObject();
		treeNode=new TreeNode();
		treeNode.setChilldren(new JSONArray());
		treeNode.setId("101");
		treeNode.setParentId("101");
		//when
		treeRepository.addChild(treeNode);
		
	}
}
