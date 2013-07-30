package com.cs.model;

import java.io.Serializable;

import com.cs.data.core.GenericDomain;

public class Attribute implements Serializable,GenericDomain{
	
	/**
	 * Default serial id
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String type;
	private String parentId;
	@Override
	public String toString() {
		return "Attribute [id=" + id + ", type=" + type + ", parentId="
				+ parentId + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
		return "ATTRIBUTE";
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return getId();
	}

	
}
