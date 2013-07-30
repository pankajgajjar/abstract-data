package com.cs.model;

import java.io.Serializable;
import java.util.List;

import com.cs.data.core.GenericDomain;

public class GenericDimension implements Serializable, GenericDomain {

	/**
	 * Initial serialVersionId
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String data;

	private List<GenericDimension> children;

	private Attribute attr;

	@Override
	public String getObjectKey() {
		// TODO Auto-generated method stub
		return "GENERICDOMAIN";
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return getId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<GenericDimension> getChildren() {
		return children;
	}

	public void setChildren(List<GenericDimension> children) {
		this.children = children;
	}

	public Attribute getAttr() {
		return attr;
	}

	public void setAttr(Attribute attr) {
		this.attr = attr;
	}

}
