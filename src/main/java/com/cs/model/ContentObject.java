package com.cs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cs.data.core.GenericDomain;
import com.sun.org.apache.regexp.internal.recompile;

@Component
public class ContentObject implements Serializable, GenericDomain {

	private final String PAGE = "page";

	private String id;
	private String type;
	private String path;
	private String name;
	private String title;
	private String isFolder;

	public ContentObject() {
		// TODO Auto-generated constructor stub
	}

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
	private List<ContentObject> children;

	public List<String> getGroupId() {
		return groupIds;
	}

	public ContentObject(String id, String type, String title, String name,
			String path) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.title = title;
		this.path = path;
	}

	public ContentObject(String id, String type, String path, String name,
			List<String> groupId, List<ContentObject> children) {
		super();
		this.id = id;
		this.type = type;
		this.path = path;
		this.name = name;
		this.groupIds = groupId;
		this.children = children;
	}

	public void setGroupId(List<String> groupId) {
		this.groupIds = groupId;
	}

	public List<ContentObject> getChildren() {
		return children;
	}

	public void setChildren(List<ContentObject> children) {
		this.children = children;
	}

	public ContentObject(String name, String type, String path, String isFolder) {
		this.id = name;
		this.name = name;
		this.title = name;
		this.type = type;
		this.path = path;
		this.isFolder = isFolder;

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
				+ ", title=" + title + ", name=" + name + ", groupIds="
				+ groupIds + ", children=" + children + "]";
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

	public boolean hasChildren() {
		return this.children == null ? false : true;
	}

	public boolean isPage() {
		return type == PAGE ? true : false;
	}

	public void addchild(ContentObject contentObject) {
		List<ContentObject> newChildren;
		if (this.children == null) {
			newChildren = new ArrayList<ContentObject>();
			newChildren.add(contentObject);
			this.children = newChildren;

		} else {
			this.children.add(contentObject);
		}
	}

	public void removeChild(ContentObject contentObject) {
		if (children != null) {
			this.children.remove(contentObject);
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PAGE == null) ? 0 : PAGE.hashCode());
		result = prime * result
				+ ((children == null) ? 0 : children.hashCode());
		result = prime * result
				+ ((groupIds == null) ? 0 : groupIds.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((isFolder == null) ? 0 : isFolder.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		ContentObject other = (ContentObject) obj;
		if (id.equals(other.id))
			return true;
		if (PAGE == null) {
			if (other.PAGE != null)
				return false;
		} else if (!PAGE.equals(other.PAGE))
			return false;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (groupIds == null) {
			if (other.groupIds != null)
				return false;
		} else if (!groupIds.equals(other.groupIds))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (isFolder == null) {
			if (other.isFolder != null)
				return false;
		} else if (!isFolder.equals(other.isFolder))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
