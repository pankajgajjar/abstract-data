package com.cs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cs.data.core.GenericDomain;

/**
 * The Class ContentObject. TODO implement interface TODO remove all the
 * annotation from class file TODO rename to dimensionObject
 */
@Component
public class MultiDimensionalObject implements Serializable, GenericDomain {

	/** The page. */
	private final String PAGE = "page";

	/** The id. */
	private String id;

	/** The type. */
	private String type;

	/** The path. TODO */
	private transient String path;

	/** The name. */
	private String name;

	/** The title. */
	private String title;

	/** The is folder.TODO */
	private transient boolean isFolder;

	/**
	 * Instantiates a new content object.
	 */
	public MultiDimensionalObject() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the checks if is folder.
	 * 
	 * @return the checks if is folder
	 */
	public boolean getIsFolder() {
		return isFolder;
	}

	/**
	 * Sets the checks if is folder.
	 * 
	 * @param isFolder
	 *            the new checks if is folder
	 */
	public void setIsFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** The group ids. */
	private List<String> groupIds;

	/** The children. */
	private List<MultiDimensionalObject> children;

	/**
	 * Gets the group id.
	 * 
	 * @return the group id
	 */
	public List<String> getGroupId() {
		return groupIds;
	}

	/**
	 * Instantiates a new content object.
	 * 
	 * @param id
	 *            the id
	 * @param type
	 *            the type
	 * @param title
	 *            the title
	 * @param name
	 *            the name
	 * @param path
	 *            the path
	 */
	public MultiDimensionalObject(String id, String type, String title,
			String name, String path) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.title = title;
		this.path = path;
	}

	/**
	 * Instantiates a new content object.
	 * 
	 * @param id
	 *            the id
	 * @param type
	 *            the type
	 * @param path
	 *            the path
	 * @param name
	 *            the name
	 * @param groupId
	 *            the group id
	 * @param children
	 *            the children
	 */
	public MultiDimensionalObject(String id, String type, String path,
			String name, List<String> groupId,
			List<MultiDimensionalObject> children) {
		super();
		this.id = id;
		this.type = type;
		this.path = path;
		this.name = name;
		this.groupIds = groupId;
		this.children = children;
	}

	/**
	 * Sets the group id.
	 * 
	 * @param groupId
	 *            the new group id
	 */
	public void setGroupId(List<String> groupId) {
		this.groupIds = groupId;
	}

	/**
	 * Gets the children.
	 * 
	 * @return the children
	 */
	public List<MultiDimensionalObject> getChildren() {
		return children;
	}

	/**
	 * Sets the children.
	 * 
	 * @param children
	 *            the new children
	 */
	public void setChildren(List<MultiDimensionalObject> children) {
		this.children = children;
	}

	/**
	 * Instantiates a new content object.
	 * 
	 * @param name
	 *            the name
	 * @param type
	 *            the type
	 * @param path
	 *            the path
	 * @param isFolder
	 *            the is folder
	 */
	public MultiDimensionalObject(String name, String type, String path,
			boolean isFolder) {
		this.id = name;
		this.name = name;
		this.title = name;
		this.type = type;
		this.path = path;
		this.isFolder = isFolder;

	}

	/**
	 * Gets the path.
	 * 
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the path.
	 * 
	 * @param path
	 *            the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.core.GenericDomain#getObjectKey()
	 */
	@Override
	public String getObjectKey() {
		// TODO Auto-generated method stub
		return "DIMENSION";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.core.GenericDomain#getKey()
	 */
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DimensionModel [id=" + id + ", type=" + type + ", path=" + path
				+ ", title=" + title + ", name=" + name + ", groupIds="
				+ groupIds + ", children=" + children + "]";
	}

	/**
	 * Checks if is root.
	 * 
	 * @return true, if is root
	 */
	public boolean isRoot() {
		return path == "-1" ? true : false;
	}

	/**
	 * Adds the to group id.
	 * 
	 * @param groupId
	 *            the group id
	 */
	public void addToGroupId(String groupId) {
		if (groupIds == null) {
			groupIds = new ArrayList<String>();
			groupIds.add(groupId);
		} else {

			groupIds.add(groupId);
		}

	}

	/**
	 * Checks for children.
	 * 
	 * @return true, if successful
	 */
	public boolean hasChildren() {
		return this.children == null ? false : true;
	}

	/**
	 * Checks if is page.
	 * 
	 * @return true, if is page
	 */
	public boolean isPage() {
		return type == PAGE ? true : false;
	}

	/**
	 * Addchild.
	 * 
	 * @param contentObject
	 *            the content object
	 */
	public void addchild(MultiDimensionalObject contentObject) {
		List<MultiDimensionalObject> newChildren;
		if (this.children == null) {
			newChildren = new ArrayList<MultiDimensionalObject>();
			newChildren.add(contentObject);
			this.children = newChildren;

		} else {
			this.children.add(contentObject);
		}
	}

	/**
	 * Removes the child.
	 * 
	 * @param contentObject
	 *            the content object
	 */
	public void removeChild(MultiDimensionalObject contentObject) {
		if (children != null) {
			this.children.remove(contentObject);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
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

		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		MultiDimensionalObject other = (MultiDimensionalObject) obj;
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
		}
		return true;
	}
}
