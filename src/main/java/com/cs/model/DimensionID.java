package com.cs.model;

import java.io.Serializable;

import com.cs.data.core.GenericDomain;

public class DimensionID implements Serializable, GenericDomain {

	private String id;
	
	private int counter;
	
	public DimensionID() {
		// TODO Auto-generated constructor stub
	}

	public DimensionID(String id) {
		// TODO Auto-generated constructor stub
		this.id=id;
	}

	public DimensionID(String id,int counter) {
		// TODO Auto-generated constructor stub
		this.id=id;
	}
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	@Override
	public String getObjectKey() {
		// TODO Auto-generated method stub
		return "DIMENSIONID";
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return getId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
