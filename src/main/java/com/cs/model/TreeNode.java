package com.cs.model;

import org.json.simple.JSONArray;

import com.cs.data.core.GenericDomain;

/**
 * The Class TreeNode.
 */
public class TreeNode implements GenericDomain{

	/** The id. */
	private String id;
	
	/** The chilldren. */
	private JSONArray chilldren;
	
	/** The parent id. */
	private String parentId;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gets the chilldren.
	 *
	 * @return the chilldren
	 */
	public JSONArray getChilldren() {
		return chilldren;
	}
	
	/**
	 * Sets the chilldren.
	 *
	 * @param chilldren the new chilldren
	 */
	public void setChilldren(JSONArray chilldren) {
		this.chilldren = chilldren;
	}
	
	/**
	 * Gets the parent id.
	 *
	 * @return the parent id
	 */
	public String getParentId() {
		return parentId;
	}
	
	/**
	 * Sets the parent id.
	 *
	 * @param parentId the new parent id
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	/* (non-Javadoc)
	 * @see com.cs.data.core.GenericDomain#getObjectKey()
	 */
	@Override
	public String getObjectKey() {
		// TODO Auto-generated method stub
		return "TreeNode";
	}
	
	/* (non-Javadoc)
	 * @see com.cs.data.core.GenericDomain#getKey()
	 */
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return getId();
	}
	
	

}
