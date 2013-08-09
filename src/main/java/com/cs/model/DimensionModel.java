package com.cs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cs.data.core.GenericDomain;

@Component
public class DimensionModel implements Serializable, GenericDomain {
	private String id;
	private String type;
	private String path;
	private String name;
	private String title;
	private String isFolder;

	public String getIsFolder() {
		return isFolder;
	}

	public void setIsFolder(String isFolder) {
		this.isFolder = isFolder;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private List<String> groupIds;
	private List<DimensionModel> children;

	public List<String> getGroupId() {
		return groupIds;
	}

	public void setGroupId(List<String> groupId) {
		this.groupIds = groupId;
	}

	public List<DimensionModel> getChildren() {
		return children;
	}

	public void setChildren(List<DimensionModel> children) {
		this.children = children;
	}

	public DimensionModel(String id, String type, String title, String name,
			String path) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.title = title;
		this.path = path;
	}

	public DimensionModel(String id, String type, String path, String name,
			List<String> groupId, List<DimensionModel> children) {
		super();
		this.id = id;
		this.type = type;
		this.path = path;
		this.name = name;
		this.groupIds = groupId;
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
		return "DimensionModel [id=" + id + ", type=" + type + ", path=" + path
				+ ", name=" + name + ", groupIds=" + groupIds + ", children="
				+ children + "]";
	}

	public boolean isRoot() {
		return path == "-1" ? true : false;
	}

	public void addToGroupId(String groupId) {
		if (groupIds == null) {
			groupIds = new ArrayList<String>();
			groupIds.add(groupId);
		} else {

			groupIds.add(groupId);
		}

	}
}
