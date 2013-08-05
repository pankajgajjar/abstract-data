package com.cs.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cs.model.DimensionGroup;
import com.cs.model.DimensionModel;
import com.cs.model.Tree;
import com.cs.repository.CubeRepository;
import com.cs.repository.DimensionGroupRepository;
import com.cs.repository.TreeRepository;
import com.cs.utils.FileUtils;

@Controller
public class ContentTreeController {

	private TreeRepository treeRepository;

	private DimensionGroupRepository groupRepository;

	@Autowired
	public ContentTreeController(TreeRepository treeRepository,
			DimensionGroupRepository groupRepository) {

		this.treeRepository = treeRepository;
		this.groupRepository = groupRepository;

	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String getIndexPage() {
		return "redirect:/pages/index.html";
	}

	@RequestMapping(value = { "/create/{type}/name/{name}/parentId/{parentId}/path/{path}" }, method = RequestMethod.GET)
	public @ResponseBody
	JSONObject create(@PathVariable("type") String type,
			@PathVariable("name") String name,
			@PathVariable("path") String path,
			@PathVariable("parentId") String parentId)
			throws InterruptedException, IOException, URISyntaxException,
			ParseException {
		String id = treeRepository.getRandomKey() + "";
		JSONObject atrr = new JSONObject();
		JSONObject innerObject = new JSONObject();
		Tree tree = new Tree();

		if (parentId.equals("-1")
				&& treeRepository.getTree("treeDemo01", "TREE") == null) {

			tree.setId("treeDemo01");

			innerObject.put("id", id);

			innerObject.put("parentId", parentId);
			innerObject.put("type", type);
			innerObject.put("path", path);
			atrr.put("attr", innerObject);
			atrr.put("data", name);
			atrr.put("children", new JSONArray());
			atrr.put("path", path);
			JSONArray array = new JSONArray();
			array.add(atrr);
			tree.setTreeData(array);
			treeRepository.createTree(tree);
		}

		else {
			innerObject.put("id", id);
			innerObject.put("parentId", parentId);
			innerObject.put("type", type);
			innerObject.put("path", path);
			atrr.put("attr", innerObject);
			atrr.put("data", name);
			atrr.put("children", new JSONArray());
			tree = treeRepository.getTree("treeDemo01", "TREE");
			treeRepository.addChild(atrr, parentId, tree);

		}
		savePath(path);
		JSONObject response = new JSONObject();
		response.put("id", id);
		response.put("path", path);

		return response;

	}

	private void savePath(String path) throws IOException, URISyntaxException,
			ParseException {
		// TODO Auto-generated method stub

		JSONObject view = getCurrentSchema();
		String[] dimensions = path.split(",");
		DimensionGroup group;

		// ArrayList<String> keys = getOrderedDimensions(view);

		ArrayList<String> keys = new ArrayList<String>();
		keys.add("PublicationGroup");
		keys.add("Campaign");
		keys.add("Publication");
		keys.add("MasterPublication");

		DimensionModel model;
		int i = 0;
		if (dimensions.length == keys.size()) {
			ArrayList<DimensionModel> dim = new ArrayList<DimensionModel>();
			String groupId = treeRepository.getRandomKey() + "";
			group = new DimensionGroup();
			group.setId(groupId);
			group.setGroupName("group" + groupId);
			for (String dimension : dimensions) {

				String id = treeRepository.getRandomKey() + "";
				model = new DimensionModel();

				model.setId(dimension);
				model.setName(dimension);
				model.setType(keys.get(i));
				dim.add(model);

				i++;
			}

			group.setDimensions(dim);
			groupRepository.save(group);
		}

	}

	private ArrayList<String> getOrderedDimensions(JSONObject view)
			throws IOException, URISyntaxException, ParseException {
		ArrayList<String> keys = null;

		/*
		 * JSONObject object = getCurrentSchema(); while
		 * (object.keySet().contains()) keys.addAll(object.keySet());
		 */
		return keys;

	}

	private JSONObject getCurrentSchema() throws ParseException, IOException,
			URISyntaxException {
		FileUtils fileUtils = new FileUtils();
		String viewStructure = fileUtils.getFileContents("schema1.json");
		return (JSONObject) new JSONParser().parse(viewStructure);
	}

	@RequestMapping(value = { "/get/{schema}" }, method = RequestMethod.GET)
	public @ResponseBody
	String getTree(@PathVariable String schema) throws ParseException,
			IOException, URISyntaxException {
		// TODO Auto-generated method stub

		// JSONObject schema = getCurrentSchema();
		String[] schemaKeys = schema.split("-");

		System.out.println(schemaKeys);
		ArrayList<String> keys = new ArrayList<String>();
		for (String key : schemaKeys) {
			keys.add(key);
			System.out.println(key);
			
		}

		CubeRepository cube = new CubeRepository(groupRepository);
		List<DimensionGroup> groups = cube.fetchAllGroups();
		cube.applyRule(keys, groups);

		return cube.getTree().toJSONString();
	}

}