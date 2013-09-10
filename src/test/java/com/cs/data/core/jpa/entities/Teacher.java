package com.cs.data.core.jpa.entities;

import java.io.Serializable;
import java.util.List;

import com.cs.data.core.GenericDomain;

public class Teacher implements Serializable, GenericDomain {

	private String id;
	private List<Student> students;

	public Teacher(String id, List<Student> students) {
		super();
		this.id = id;
		this.students = students;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String getObjectKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
