package com.cs.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.cs.data.core.GenericDomain;

@Component
public class DimensionModel implements Serializable, GenericDomain {

	public DimensionModel(String id, String type, String name) {
		this.id = id;
		this.type = type;
		this.name = name;
	}

	public DimensionModel() {
		// TODO Auto-generated constructor stub
	}

	private String id;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getObjectKey() {
		// TODO Auto-generated method stub
		return "DIMENSION";
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return getId();
	}

	@Override
	public String toString() {
		return "DM <id=" + id + ", type=" + type + ", name=" + name + ">";
	}

}
