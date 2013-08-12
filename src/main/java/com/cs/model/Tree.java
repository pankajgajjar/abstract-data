package com.cs.model;

import java.io.Serializable;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Component;

import com.cs.data.core.GenericDomain;

/**
 * The Class Tree.
 */
@Component
public class Tree implements GenericDomain, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private String id;
	
	/** The tree data. */
	private JSONArray treeData;

	/**
	 * Instantiates a new tree.
	 *
	 * @param id the id
	 * @param treeData the tree data
	 */
	public Tree(String id, JSONArray treeData) {
		this.id = id;
		this.treeData = treeData;
	}
	
	/**
	 * Instantiates a new tree.
	 */
	public Tree() {
		
	}

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
	 * Gets the tree data.
	 *
	 * @return the tree data
	 */
	public JSONArray getTreeData() {
		return treeData;
	}

	/**
	 * Sets the tree data.
	 *
	 * @param treeData the new tree data
	 */
	public void setTreeData(JSONArray treeData) {
		this.treeData = treeData;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TreeModel [id=" + id + ", treeData=" + treeData + "]";
	}

	/* (non-Javadoc)
	 * @see com.cs.data.core.GenericDomain#getObjectKey()
	 */
	@Override
	public String getObjectKey() {
		return "TREE";
	}

	/* (non-Javadoc)
	 * @see com.cs.data.core.GenericDomain#getKey()
	 */
	@Override
	public String getKey() {
		return getId();
	}

}
