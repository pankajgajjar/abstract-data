package com.pub.data.abstraction.core;

public class Student {
	private String id;
	private String name;
	private String standard;
	
	public Student(String id,String name,String standard)
	{
		this.id=id;
		this.name=name;
		this.standard=standard;
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
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}

}
