package com.cs.model;

import java.io.Serializable;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.cs.data.core.GenericDomain;


/**
 * The Class DimensionGroup.
 */
@Document
@Component
public class DimensionGroup implements Serializable, GenericDomain {

	/** Initial ID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private String id;
	
	/** The group name. */
	private String groupName;

	/** The dimensions. */
	private List<ContentObject> dimensions;
	
	/** The existence. */
	private boolean existence;

	/**
	 * Instantiates a new dimension group.
	 */
	public DimensionGroup() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DimensionGroup [id=" + id + ", groupName=" + groupName
				+ ",\n\n dimensions=" + dimensions + "]";
	}

	/**
	 * Instantiates a new dimension group.
	 *
	 * @param id the id
	 * @param groupName the group name
	 * @param dimensions the dimensions
	 */
	public DimensionGroup(String id, String groupName,
			List<ContentObject> dimensions) {
		this.id = id;
		this.groupName = groupName;
		this.dimensions = dimensions;
	}

	/**
	 * Gets the dimensions.
	 *
	 * @return the dimensions
	 */
	public List<ContentObject> getDimensions() {
		return dimensions;
	}

	/**
	 * Sets the dimensions.
	 *
	 * @param dimensions the new dimensions
	 */
	public void setDimensions(List<ContentObject> dimensions) {
		this.dimensions = dimensions;
	}

	/**
	 * Instantiates a new dimension group.
	 *
	 * @param id the id
	 * @param groupName the group name
	 */
	public DimensionGroup(String id, String groupName) {
		this.id = id;
		this.groupName = groupName;
	}

	/**
	 * Gets the group name.
	 *
	 * @return the group name
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * Sets the group name.
	 *
	 * @param groupName the new group name
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	/* (non-Javadoc)
	 * @see com.cs.data.core.GenericDomain#getObjectKey()
	 */
	@Override
	public String getObjectKey() {
		// TODO Auto-generated method stub
		return "DIMENSIONGROUP";
	}

	/* (non-Javadoc)
	 * @see com.cs.data.core.GenericDomain#getKey()
	 */
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return getId();
	}

	/**
	 * Exists.
	 *
	 * @return true, if successful
	 */
	public boolean exists() {
		// TODO Auto-generated method stub
		return existence;
	}

	/**
	 * Toggle existence.
	 *
	 * @param existence the existence
	 */
	public void toggleExistence(boolean existence) {
		this.existence = existence;

	}

}
