package com.cs.factory;

import com.cs.model.Tree;

public class TreeFactory {
	
	public Tree getTreeInstance(String parentId){
		
		if(parentId.equals("-1"))
			return new Tree();
		else{
			return new Tree();
		}
	}

}
