package com.cs.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cs.data.core.GenericDomain;

@Component
public class DimensionModel implements Serializable, GenericDomain {
	private String id;
	private String type;
	private String path;
	private String name;
	private List<String> groupId;
	private List<DimensionModel> children;

	public List<String> getGroupId() {
		return groupId;
	}

	public void setGroupId(List<String> groupId) {
		this.groupId = groupId;
	}

	public List<DimensionModel> getChildren() {
		return children;
	}

	public void setChildren(List<DimensionModel> children) {
		this.children = children;
	}

	public DimensionModel(String id, String type, String name, String path) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.path = path;
	}

	public DimensionModel(String id, String type, String path, String name,
			List<String> groupId, List<DimensionModel> children) {
		super();
		this.id = id;
		this.type = type;
		this.path = path;
		this.name = name;
		this.groupId = groupId;
		this.children = children;
	}

	public DimensionModel() {
		// TODO Auto-generated constructor stub
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

	public boolean isRoot() {
		return path == "-1" ? true : false;
	}

}
