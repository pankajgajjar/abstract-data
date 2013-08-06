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
		return noSqlTemplateForRedis.save(tree);

	}

	public int getRandomKey() {
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

		if (((JSONObject) objectToInsert.get("attr")).get("parentId").equals(
				"-1")) {
			treeJson.add(objectToInsert);
			return treeJson;
		}

		for (Object object : treeJson) {
			// Object object = treeJson.get(i++);

			jsonObject = (JSONObject) object;
			JSONObject attrJson = (JSONObject) jsonObject.get("attr");
			if (attrJson.get("id").equals(parentId)) {

				Object children = jsonObject.get("children");
				JSONArray tempArray = null;
				if (children == null) {
					tempArray = new JSONArray();
				} else {
					tempArray = (JSONArray) children;
				}
				tempArray.add(objectToInsert);
				jsonObject.put("children", tempArray);
				return treeJson;
			}

			else if (jsonObject.get("children") != null) {
				// JSONArray temp = new JSONArray();
				// temp.add(jsonObject.get("children"));
				updateAndModify((JSONArray) jsonObject.get("children"),
						parentId, objectToInsert);
			}

		}
		return treeJson;

		// TODO Auto-generated method stub

	}

	public Tree getTree(String key, String objectKey) {
		Tree tree = noSqlTemplateForRedis.getObjectByKey(key, objectKey,
				Tree.class);
		return tree;

	}

}
