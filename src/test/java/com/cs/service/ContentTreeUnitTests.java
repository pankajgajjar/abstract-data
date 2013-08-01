package com.cs.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.model.Tree;
import com.cs.repository.DimensionGroupRepository;
import com.cs.repository.TreeRepository;
import com.cs.service.ContentTree;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ContentTreeUnitTests {

	private ContentTree treeController;

	@Mock
	private TreeRepository treeRepository;
	
	@Mock 
	DimensionGroupRepository groupRepository;
	
	@Mock
	Tree testTree;

	@Before
	public void setUp() {
		treeController = new ContentTree(treeRepository,groupRepository);
	}

	@Test
	public void itShouldReturnIndexPage() {
		// given
		String expectedPage = "redirect:/pages/index.html";

		// when
		String actualPage = treeController.getIndexPage();

		// then

		assertThat(actualPage).isEqualTo(expectedPage);

	}

	@Test
	public void itShouldCreateNewTree() throws InterruptedException, IOException, URISyntaxException, ParseException {

		// given

		JSONObject firstElement = new JSONObject();
		firstElement.put("id", "100");
		firstElement.put("name", "c02");
		firstElement.put("children", new JSONArray());

		JSONObject attr = new JSONObject();
		attr.put("attr", firstElement);
		JSONArray treeData = new JSONArray();
		treeData.add(attr);
		Tree tree = new Tree();
		tree.setId("tree01");
		tree.setTreeData(treeData);

		String testResult = "success";
		JSONObject expected =new JSONObject();
		expected.put("path", "100");
		
		// when
		when(treeRepository.createTree(tree)).thenReturn(testResult);
		JSONObject actualresult = treeController.create("dim1", "cp01", "100","TEST");
		// then
		assertThat(actualresult.get("path")).isEqualTo(expected.get("path"));

	}

	@Test
	public void itShouldReturnTree() throws ParseException, IOException, URISyntaxException {
		// given
	
		JSONArray array=new JSONArray();
		array.add("test");

		// when
		when(treeRepository.getTree("treeDemo01", "TREE")).thenReturn(testTree);
		when(testTree.getTreeData()).thenReturn(array);
		String actualTree = treeController.getTree("default");

		// then
		//verify(treeRepository).getTree("treeDemo01", "TREE");
		assertThat(actualTree).isEqualTo(array.toString());
	}

	@Test
	public void itShouldAddAnElementToTree() {

		// given
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", "200");
		jsonObject.put("name", "mp02");

		String result = "success";
		String parentId = "100";
		Tree stubbedtree = new Tree("1", new JSONArray());

		when(treeRepository.getTree("1", "TREE")).thenReturn(stubbedtree);
		Tree tree = treeRepository.getTree("1", "TREE");

		when(treeRepository.addChild(jsonObject, parentId, tree)).thenReturn(
				result);
		// when
		// String parent=treeController.add(jsonObject);

		// then
	}
}
