package com.cs.model;

import java.io.Serializable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.cs.data.core.GenericDomain;

@Component
public class Tree implements GenericDomain,Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private JSONArray treeData;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JSONArray getTreeData() {
		return treeData;
	}

	public void setTreeData(JSONArray treeData) {
		this.treeData = treeData;
	}

	@Override
	public String toString() {
		return "TreeModel [id=" + id + ", treeData=" + treeData + "]";
	}
	
	@Override
	public String getObjectKey(){
		return "TREE";
	}
	
	@Override
	public String getKey(){
		return getId();
	}

	

}
