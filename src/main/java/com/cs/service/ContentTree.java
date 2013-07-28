package com.cs.service;

import javax.transaction.Synchronization;

import org.hibernate.annotations.Synchronize;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cs.data.core.IRepository;
import com.cs.factory.IDFactory;
import com.cs.model.Tree;
import com.cs.repository.TreeRepository;

@Controller
public class ContentTree {

	private TreeRepository treeRepository;

	@Autowired
	public ContentTree(TreeRepository treeRepository) {

		this.treeRepository = treeRepository;

	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String getIndexPage() {
		return "redirect:/pages/index.html";
	}

	@RequestMapping(value = { "/create/{type}/name/{name}/parentId/{parentId}" }, method = RequestMethod.GET)
	public @ResponseBody
	String create(@PathVariable("type") String type,
			@PathVariable("name") String name,
			@PathVariable("parentId") String parentId)
			throws InterruptedException {

		String id = treeRepository.getRandomKey() + "";
		JSONObject atrr = new JSONObject();
		JSONObject innerObject = new JSONObject();
		Tree tree = new Tree();

		if (parentId.equals("-1")
				&& treeRepository.getTree("tree001", "TREE") == null) {

			tree.setId("tree001");

			innerObject.put("id", id);

			innerObject.put("parentId", parentId);
			innerObject.put("type", type);
			atrr.put("attr", innerObject);
			atrr.put("data", name);
			atrr.put("children", new JSONArray());
			JSONArray array = new JSONArray();
			array.add(atrr);
			tree.setTreeData(array);
			treeRepository.createTree(tree);
		}

		else {
			innerObject.put("id", id);
			innerObject.put("parentId", parentId);
			innerObject.put("type", type);
			atrr.put("attr", innerObject);
			atrr.put("data", name);
			atrr.put("children", new JSONArray());
			tree = treeRepository.getTree("tree001", "TREE");
			treeRepository.addChild(atrr, parentId, tree);

		}
		return treeRepository.getTree("tree001", "TREE").getTreeData()
				.toString();

	}

	@RequestMapping(value = { "/get" }, method = RequestMethod.GET)
	public @ResponseBody
	String getTree() {
		// TODO Auto-generated method stub
		String key = "tree01";
		String objectKey = "TREE";

		return treeRepository.getTree("tree001", "TREE").getTreeData()
				.toString();
	}

}
