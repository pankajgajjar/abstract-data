package com.cs.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.model.Tree;
import com.cs.repository.TreeRepository;
import com.cs.service.ContentTree;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ContentTreeUnitTests {

	private ContentTree treeController;

	@Mock
	private TreeRepository treeRepository;

	@Before
	public void setUp() {
		treeController = new ContentTree(treeRepository);
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
	public void itShouldCreateNewTree() {

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
		String expected = "{'status':'OK'}";
		// when
		when(treeRepository.createTree(tree)).thenReturn(testResult);
		String actualresult = treeController.create("dim1", "cp01", "100");
		// then
		assertThat(actualresult).isEqualTo(expected);

	}

	@Test
	public void itShouldReturnTree() {
		// given
		Tree testTree = new Tree();
		testTree.setId("tree01");

		// when
		when(treeRepository.getTree("tree01", "TREE")).thenReturn(testTree);
		Tree actualTree = treeController.getTree();

		// then
		verify(treeRepository).getTree("tree01", "TREE");
		assertThat(actualTree).isEqualTo(testTree);
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
