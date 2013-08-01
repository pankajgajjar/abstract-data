package com.cs.model;

import java.io.Serializable;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cs.data.core.GenericDomain;

public class DimensionGroup implements Serializable, GenericDomain {

	/**
	 * Initial ID
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String groupName;

	private List<DimensionModel> dimensions;

	public DimensionGroup() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "DimensionGroup [id=" + id + ", groupName=" + groupName
				+ ",\n\n dimensions=" + dimensions + "]";
	}

	public DimensionGroup(String id, String groupName,
			List<DimensionModel> dimensions) {
		this.id = id;
		this.groupName = groupName;
		this.dimensions = dimensions;
	}

	public List<DimensionModel> getDimensions() {
		return dimensions;
	}

	public void setDimensions(List<DimensionModel> dimensions) {
		this.dimensions = dimensions;
	}

	public DimensionGroup(String id, String groupName) {
		this.id = id;
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getObjectKey() {
		// TODO Auto-generated method stub
		return "DIMENSIONGROUP";
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return getId();
	}

}
