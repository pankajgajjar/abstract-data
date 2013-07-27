package com.cs.repository;

import java.util.Iterator;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cs.data.core.nosql.NoSqlOperations;
import com.cs.data.core.nosql.redis.RedisRepository;
import com.cs.model.Tree;

public class TreeRepository {

	private NoSqlOperations iRepository;

	public TreeRepository(NoSqlOperations noSqlOperationsForRedis) {
		// TODO Auto-generated constructor stub
		this.iRepository = noSqlOperationsForRedis;

	}

	public String createTree(Tree tree) {
		
		return persist(tree);
		// TODO Auto-generated method stub
		

	}

	private String persist(Tree tree) {
		// TODO Auto-generated method stub
		return iRepository.insert(tree);

	}

	private Object getRandomKey() {
		// TODO Auto-generated method stub
		return new Random().nextInt(10000);
	}

	public void addNode(String nodeName) {
		// TODO Auto-generated method stub

	}

	public void addChild(JSONObject objectToAdd,String parentId,Tree tree) {
		
		
		JSONArray treeJson=tree.getTreeData();
		JSONArray updated=updateAndModify(treeJson,parentId,objectToAdd);
		tree.setId("11");
		tree.setTreeData(updated);
		persist(tree);
		System.out.println(tree);
		
		
	}

	private JSONArray updateAndModify(JSONArray treeJson, String parentId,JSONObject objectToInsert) {
		int i=0;
		JSONObject jsonObject=new JSONObject();
		Iterator<JSONObject> iterator=treeJson.iterator();
		while(iterator.hasNext()){
			Object object=treeJson.get(i);
			System.out.println(object);
			jsonObject=(JSONObject) object;
			if(jsonObject.get("id").equals(parentId))
			{
				jsonObject.put("children", objectToInsert);
				treeJson.add(jsonObject);
				return treeJson;
			}
			
			if(jsonObject.get("children")!=null){
				JSONArray temp=new JSONArray();
				temp.add(jsonObject.get("children"));
				return updateAndModify(temp, parentId, objectToInsert);
			}
			
			i++;
			
		}
		return null;
		
		// TODO Auto-generated method stub
		
	}

	public Tree getTree(String key,String objectKey) {
		Tree tree = iRepository.getObjectByKey(key,objectKey, Tree.class);
		return tree;

	}

}
