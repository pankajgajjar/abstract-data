package com.cs.model;

import java.io.Serializable;

import com.cs.data.core.GenericDomain;

/**
 * The Class DimensionID.
 */
public class DimensionID implements Serializable, GenericDomain {

	/** The id. */
	private String id;
	
	/** The counter. */
	private int counter;
	
	/**
	 * Instantiates a new dimension id.
	 */
	public DimensionID() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new dimension id.
	 *
	 * @param id the id
	 */
	public DimensionID(String id) {
		// TODO Auto-generated constructor stub
		this.id=id;
	}

	/**
	 * Instantiates a new dimension id.
	 *
	 * @param id the id
	 * @param counter the counter
	 */
	public DimensionID(String id,int counter) {
		// TODO Auto-generated constructor stub
		this.id=id;
	}
	
	/**
	 * Gets the counter.
	 *
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * Sets the counter.
	 *
	 * @param counter the new counter
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	/* (non-Javadoc)
	 * @see com.cs.data.core.GenericDomain#getObjectKey()
	 */
	@Override
	public String getObjectKey() {
		// TODO Auto-generated method stub
		return "DIMENSIONID";
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

}
