package com.cs.model;

import org.json.simple.JSONArray;

import com.cs.data.core.GenericDomain;

public class TreeNode implements GenericDomain{

	private String id;
	private JSONArray chilldren;
	private String parentId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public JSONArray getChilldren() {
		return chilldren;
	}
	public void setChilldren(JSONArray chilldren) {
		this.chilldren = chilldren;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Override
	public String getObjectKey() {
		// TODO Auto-generated method stub
		return "TreeNode";
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return getId();
	}
	
	

}
