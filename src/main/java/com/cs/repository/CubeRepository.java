/*package com.cs.repository;

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
		System.out.println("No of dimensions=====>"
				+ arrayOfAllIndividualPaths.size());
		for (DimensionGroup group : dimensionGroups) {
			arrayOfAllIndividualPaths.add(getSinglePath(rules, group));
		}
		return arrayOfAllIndividualPaths;
	}

	private JSONObject getSinglePath(ArrayList<String> rules,
			DimensionGroup group) {
		JSONArray models = new JSONArray();
		System.out.println("Rules======> " + rules);
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

		System.out.println("Joining path============");
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
			 

		}

	}

	public void addModelToCurrentThread(JSONArray model, JSONObject nodePath) {
		JSONObject parent = null;

		boolean flag = true;
		for (Object object : model) {

			JSONObject jsonObject = (JSONObject) object;
			System.out.println("In function addModelToCurrentThread:=> Model= "
					+ jsonObject + "=========>" + (nodePath.get("id")));
			System.out
					.println("In function addModelToCurrentThread:=> Condition for checking ids "
							+ jsonObject.get("id")
							+ "=========>"
							+ (nodePath.get("id")));
			if (jsonObject.get("id").equals(nodePath.get("id"))) {
				parent = nodePath;
				JSONArray tempArray = (JSONArray) nodePath.get("children");
				if (tempArray != null)
					nodePath = (JSONObject) tempArray.get(0);

				continue;
			} else {

				System.out
						.println("In function addModelToCurrentThread:=> ID Not matched= So Parent==>"
								+ parent
								+ "having children=========>"
								+ parent.get("children"));
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
						if (jsonObj.get("id").equals(tempo.get("id"))) {

							System.out.println("True for " +jsonObj.get("id")+"======="+tempo.get("id") );
							JSONArray childrenOfSameNode = (JSONArray) jsonObj
									.get("children");
							
							//object=childrenOfSameNode.get(0);
							
							if(childrenOfSameNode!=null){
								childrenOfSameNode.add(object);
								jsonObj.put("children",childrenOfSameNode);
							}
							else{
								JSONArray chillld=new JSONArray();
								chillld.add(object);
								jsonObj.put("children",chillld);
							}
							isEqual = false;
						}

					}

				}

				if (isEqual) {
					children.add(object);

					parent.put("children", children);
				}

				System.out
						.println("In function addModelToCurrentThread:=> Parent After Adding Children==>"
								+ parent
								+ "having children=========>"
								+ parent.get("children"));
				parent = (JSONObject) object;

				// nodePath = (JSONObject) object;
				System.out
						.println("In function addModelToCurrentThread:=> Current Parent ==>"
								+ parent);

				System.out
						.println("In function addModelToCurrentThread:=> ID Not matched= So Parent==>"
								+ parent
								+ "having children=========>"
								+ parent.get("children"));

			}

		}

	}

	public JSONArray getTree() {
		return finalTree;

	}

}
*/