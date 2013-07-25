package com.cs.model;

import java.io.Serializable;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.cs.data.core.GenericDomain;

@Component
public class Tree implements GenericDomain,Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private JSONObject treeData;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JSONObject getTreeData() {
		return treeData;
	}

	public void setTreeData(JSONObject treeData) {
		this.treeData = treeData;
	}

	@Override
	public String toString() {
		return "TreeModel [id=" + id + ", treeData=" + treeData + "]";
	}
	
	@Override
	public String getObjectKey(){
		return "Tree";
	}
	
	@Override
	public String getKey(){
		return getId();
	}

	

}
