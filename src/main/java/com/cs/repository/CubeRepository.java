package com.cs.repository;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.cs.model.DimensionGroup;
import com.cs.model.DimensionModel;

public class CubeRepository {

	private DimensionGroupRepository groupRepository;

	private JSONArray finalTree = new JSONArray();

	private ArrayList<JSONArray> refModels = new ArrayList<JSONArray>();

	@Autowired
	public CubeRepository(DimensionGroupRepository groupRepository) {

		this.groupRepository = groupRepository;
	}

	public List<DimensionGroup> fetchAllGroups() {
		// TODO Auto-generated method stub
		return groupRepository.findBy(null);
	}

	public JSONArray applyRule(ArrayList<String> rules,
			List<DimensionGroup> dimensionGroups) {

		JSONArray arrayOfAllIndividualPaths = new JSONArray();
		for (DimensionGroup group : dimensionGroups) {
			arrayOfAllIndividualPaths.add(getSinglePath(rules, group));
		}
		return arrayOfAllIndividualPaths;
	}

	private JSONObject getSinglePath(ArrayList<String> rules,
			DimensionGroup group) {
		JSONArray models = new JSONArray();
		System.out.println("Rules " + rules);
		for (String rule : rules) {

			for (DimensionModel model : group.getDimensions()) {
				if (rule.equals(model.getType())) {

					JSONObject attr = new JSONObject();
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", model.getId());
					jsonObject.put("name", model.getName());
					jsonObject.put("type", model.getType());

					attr.put("attr", jsonObject);
					attr.put("id", model.getId());
					attr.put("data", model.getName());
					attr.put("type", model.getType());

					models.add(attr);

				}

			}
		}
		return joinPath(models);

	}

	private JSONObject joinPath(JSONArray models) {

		System.out.println("Models " + models);
		JSONObject parent = null;
		boolean check = true;
		JSONObject child = null;
		JSONObject root = null;
		JSONObject nodePath = new JSONObject();

		System.out.println("Final tree " + finalTree);

		for (Object object : finalTree) {
			System.out.println("in filetree for loop");
			nodePath = (JSONObject) object;

			check = check && checkWithCreatedpaths(models, nodePath);

		}

		if (check) {
			System.out.println("in check");
			for (Object model : models) {

				child = (JSONObject) model;

				if (root == null)
					root = child;
				if (parent == null)
					parent = child;
				else {
					JSONArray children = new JSONArray();
					children.add(child);
					parent.put("children", children);
					System.out.println();
					children = (JSONArray) parent.get("children");
					parent = (JSONObject) children.get(0);
				}

			}

			finalTree.add(root);
		}
		return root;
	}

	private boolean checkWithCreatedpaths(JSONArray model, JSONObject nodePath) {
		// TODO Auto-generated method stub
		System.out.println("in checkcreationcreated");
		JSONObject node = (JSONObject) model.get(0);
		if (!node.get("id").equals(nodePath.get("id"))) {
			System.out.println("similar node Not found" + nodePath.get("id"));
			return true;
		}

		else {
			System.out.println("similar node " + nodePath.get("id"));
			addModelToCurrentThread(model, nodePath);
			return false;
			/*
			 * if (model.get("id").equals(nodePath.get("id"))) { return true; }
			 * 
			 * else {
			 * 
			 * JSONArray children = null; if (parent.get("chlidren") == null)
			 * children = new JSONArray(); else children = (JSONArray)
			 * parent.get("chlidren"); children.add(model);
			 * 
			 * parent.put("children", children);
			 * 
			 * return true;
			 */

		}

	}

	private void addModelToCurrentThread(JSONArray model, JSONObject nodePath) {
		System.out.println("in addModelToCureent");
		JSONObject parent = null;

		boolean flag = true;
		for (Object object : model) {

			if (flag) {
				JSONObject jsonObject = (JSONObject) object;
				System.out.println(jsonObject.get("data") + "=="
						+ nodePath.get("data"));
				if (jsonObject.get("id").equals(nodePath.get("id"))) {
					parent = nodePath;
					JSONArray tempArray = (JSONArray) nodePath.get("children");
					nodePath = (JSONObject) tempArray.get(0);
					System.out.println("continue if nodes are similar "
							+ nodePath.get("id"));
					continue;
				} else {
					System.out.println("nodes are NOT similar "
							+ nodePath.get("id"));
					JSONArray children = null;
					if (parent.get("children") == null) {
						children = new JSONArray();

					}

					else {
						Object temp = parent.get("children");
						children = (JSONArray) temp;
					}

					System.out.println("parent" + parent + "Node to add"
							+ object);
					children.add(object);
					parent.put("children", children);
					System.out.println("parent" + parent);
					parent = (JSONObject) object;
					flag = false;
				}

			} else {
				JSONArray tempArray = null;
				if (parent.get("children") == null)
					tempArray = new JSONArray();
				else {
					tempArray = (JSONArray) parent.get("children");
				}
				tempArray.add(object);
				parent.put("children", tempArray);
			}

		}

	}

	public JSONArray getTree() {
		return finalTree;

	}

}
