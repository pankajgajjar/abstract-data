package com.cs.repository;

import java.util.Iterator;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.data.core.nosql.NoSqlOperations;
import com.cs.data.core.nosql.redis.RedisRepository;
import com.cs.model.Tree;

@Component
public class TreeRepository {

	private NoSqlOperations noSqlTemplateForRedis;

	@Autowired
	public TreeRepository(NoSqlOperations noSqlTemplateForRedis) {
		// TODO Auto-generated constructor stub
		this.noSqlTemplateForRedis = noSqlTemplateForRedis;

	}

	public String createTree(Tree tree) {

		return persist(tree);
		// TODO Auto-generated method stub

	}

	private String persist(Tree tree) {
		// TODO Auto-generated method stub
		return noSqlTemplateForRedis.insert(tree);

	}

	public  int getRandomKey() {
		// TODO Auto-generated method stub
		return new Random().nextInt(10000);
	}

	public void addNode(String nodeName) {
		// TODO Auto-generated method stub

	}

	public String addChild(JSONObject objectToAdd, String parentId, Tree tree) {

		JSONArray treeJson = tree.getTreeData();
		JSONArray updated = updateAndModify(treeJson, parentId, objectToAdd);
		tree.setTreeData(updated);
		return persist(tree);

	}

	private JSONArray updateAndModify(JSONArray treeJson, String parentId,
			JSONObject objectToInsert) {
		JSONObject jsonObject = new JSONObject();
		Iterator<JSONObject> iterator = treeJson.iterator();
		for(Object object : treeJson) {
			System.out.println("Test");
			//Object object = treeJson.get(i++);
			
				jsonObject = (JSONObject) object;
				jsonObject=(JSONObject) jsonObject.get("attr");
				System.out.println(jsonObject);
				if (jsonObject.get("id").equals(parentId)) {
					jsonObject.put("children", objectToInsert);
					System.out.println(treeJson);
					return treeJson;
				}
		

				else if (!jsonObject.get("children").equals(new JSONArray())) {
				JSONArray temp = new JSONArray();
				temp.add(jsonObject.get("children"));
				updateAndModify(temp, parentId, objectToInsert);
			}

			

		}
		return treeJson;

		// TODO Auto-generated method stub

	}

	public Tree getTree(String key, String objectKey) {
		Tree tree = noSqlTemplateForRedis.getObjectByKey(key, objectKey, Tree.class);
		return tree;

	}

}
