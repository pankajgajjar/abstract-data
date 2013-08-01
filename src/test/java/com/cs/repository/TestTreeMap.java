package com.cs.repository;

import static org.junit.Assert.*;

import java.util.TreeMap;

import org.junit.Test;

public class TestTreeMap {

	@Test
	public void test() {
		TreeMap< String, String> map=new TreeMap< String, String>();
		map.put("1", "2");
		map.put("2", "3");
		map.put("3", "4");
		System.out.println(map);
		
	}

}
