package com.cs.model;

import java.io.Serializable;

import com.cs.data.core.GenericDomain;


/**
 * The Class Attribute.
 */
public class Attribute implements Serializable, GenericDomain {

	/** Default serial id. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private String id;
	
	/** The type. */
	private String type;
	
	/** The parent id. */
	private String parentId;

	/**
	 * Instantiates a new attribute.
	 *
	 * @param id the id
	 * @param type the type
	 * @param parentId the parent id
	 */
	public Attribute(String id, String type, String parentId) {
		this.id = id;
		this.type = type;
		this.parentId = parentId;
	}

	/* Conversion to string.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Attribute [id=" + id + ", type=" + type + ", parentId="
				+ parentId + "]";
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
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the parent id.
	 *
	 * @return the parent id
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * Sets the parent id.
	 *
	 * @param parentId the new parent id
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/* (non-Javadoc)
	 * @see com.cs.data.core.GenericDomain#getObjectKey()
	 */
	@Override
	public String getObjectKey() {
		// TODO Auto-generated method stub
		return "ATTRIBUTE";
	}

	/* (non-Javadoc)
	 * @see com.cs.data.core.GenericDomain#getKey()
	 */
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return getId();
	}

}
