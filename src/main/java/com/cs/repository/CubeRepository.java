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

		JSONObject parent = null;
		boolean check = true;
		JSONObject child = null;
		JSONObject root = null;
		JSONObject nodePath = new JSONObject();

		for (Object object : finalTree) {
			nodePath = (JSONObject) object;

			check = check && checkWithCreatedpaths(models, nodePath);

		}

		if (check) {
			System.out.println("in check");
			for (Object model : models) {

				child = (JSONObject) model;

				if (root == null) {
					root = child;
					root.put("path", root.get("data"));
				}
				if (parent == null) {
					parent = child;
					JSONObject object = (JSONObject) child.get("attr");
					object.put("path", child.get("data"));
				} else {
					JSONArray children = new JSONArray();
					children.add(child);
					JSONObject object = (JSONObject) child.get("attr");
					object.put("path",
							((JSONObject) parent.get("attr")).get("path") + ","
									+ child.get("data"));
					parent.put("children", children);

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
		JSONObject node = (JSONObject) model.get(0);
		if (!node.get("id").equals(nodePath.get("id"))) {
			return true;
		}

		else {
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
		JSONObject parent = null;

		boolean flag = true;
		for (Object object : model) {

			JSONObject jsonObject = (JSONObject) object;

			if (jsonObject.get("id").equals(nodePath.get("id")) && flag) {
				parent = nodePath;
				JSONArray tempArray = (JSONArray) nodePath.get("children");
				nodePath = (JSONObject) tempArray.get(0);

				continue;
			} else {

				boolean isEqual = true;
				JSONArray children = null;
				if (parent.get("children") == null) {
					children = new JSONArray();

				}

				else {
					Object temp = parent.get("children");
					children = (JSONArray) temp;
					JSONObject tempo = (JSONObject) object;
					for (Object tempobj : children) {
						JSONObject jsonObj = (JSONObject) tempobj;
						if (jsonObj.get("id").equals(tempo.get("id")))
							isEqual = false;

					}
				}

				if (isEqual) {
					JSONObject path = ((JSONObject) ((JSONObject) object)
							.get("attr"));
					((JSONObject) object).put("path",
							((JSONObject) parent.get("attr")).get("path") + ","
									+ ((JSONObject) object).get("data"));
					children.add(object);

					parent.put("children", children);
				}
				parent = (JSONObject) object;

				System.out.println("Parent is equal to" + parent);
				flag = false;

			}

		}

	}

	public JSONArray getTree() {
		return finalTree;

	}

}
