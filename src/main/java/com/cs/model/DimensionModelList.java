package com.cs.model;

import java.util.List;

/**
 * The Class DimensionModelList.
 */
public class DimensionModelList {

	/** The dimensions. */
	private List<ContentObject> dimensions;

	/**
	 * Instantiates a new dimension model list.
	 */
	public DimensionModelList() {

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

}
