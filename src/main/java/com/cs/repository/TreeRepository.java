package com.cs.repository;

import java.util.Random;


import com.cs.data.core.IRepository;
import com.cs.model.Tree;
import com.cs.model.TreeNode;

public class TreeRepository {

	private IRepository iRepository;

	public TreeRepository(IRepository iRepository) {
		// TODO Auto-generated constructor stub
		this.iRepository = iRepository;

	}

	public String createTree(Tree tree) {
		// TODO Auto-generated method stub
		int id = 100;
		tree.getTreeData().put("id", id);
		
		return persist(tree);

	}

	private String persist(Tree tree) {
		// TODO Auto-generated method stub
		return iRepository.insert(tree);

	}

	private Object getRandomKey() {
		// TODO Auto-generated method stub
		return new Random().nextInt(10000);
	}

	public void addNode(String nodeName) {
		// TODO Auto-generated method stub

	}

	public void addChild(TreeNode treeNode) {
		
		Tree tree=iRepository.getObjectByKey(new Tree(), Tree.class);
		
		
	}

}
