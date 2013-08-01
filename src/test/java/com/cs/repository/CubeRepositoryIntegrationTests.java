package com.cs.repository;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.json.simple.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cs.model.DimensionGroup;
import com.cs.model.DimensionModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-test.xml")
public class CubeRepositoryIntegrationTests {

	private DimensionGroupRepository groupRepository;

	@Autowired
	private MongoOperations mongoTemplate;
	private CubeRepository cube;

	private ArrayList<String> rules;

	@Before
	public void setUp() {
		DimensionGroupRepository groupRepository = new DimensionGroupRepository(
				mongoTemplate);

		DimensionModel model1 = new DimensionModel("100", "Campaign", "CP01");
		DimensionModel model2 = new DimensionModel("101", "MasterPublication",
				"MP01");
		DimensionModel model3 = new DimensionModel("102", "PublicationGroup",
				"PG01");
		// DimensionModel model4=new DimensionModel("103","CP01","Campaign");

		DimensionModel model5 = new DimensionModel("104", "MasterPublication",
				"MP02");
		DimensionModel model6 = new DimensionModel("105", "PublicationGroup",
				"PG02");
		DimensionModel model7 = new DimensionModel("106", "Campaign", "CP02");

		DimensionModel model8 = new DimensionModel("107", "Campaign", "CP01");
		DimensionModel model9 = new DimensionModel("108", "MasterPublication",
				"MP01");
		DimensionModel model10 = new DimensionModel("109", "PublicationGroup",
				"PG03");

		DimensionModel model11 = new DimensionModel("110", "Campaign", "CP01");
		DimensionModel model12 = new DimensionModel("111", "MasterPublication",
				"MP01");
		DimensionModel model13 = new DimensionModel("112", "PublicationGroup",
				"PG05");

		DimensionModel model14 = new DimensionModel("113", "Campaign", "CP01");
		DimensionModel model15 = new DimensionModel("114", "MasterPublication",
				"MP01");
		DimensionModel model16 = new DimensionModel("115", "PublicationGroup",
				"PG04");
		
		DimensionModel model17 = new DimensionModel("116", "Publication", "P01");
		DimensionModel model18 = new DimensionModel("117", "Publication",
				"P02");
		DimensionModel model19 = new DimensionModel("118", "Publication",
				"P03");

		/*
		 * DimensionModel model8 = new DimensionModel("107",
		 * "MasterPublication", "MP"); DimensionModel model9 = new
		 * DimensionModel("108", "Campaign", "Campaign");
		 */

		ArrayList<DimensionModel> list1 = new ArrayList<DimensionModel>();

		list1.add(model1);
		list1.add(model3);
		list1.add(model2);
		list1.add(model19);

		ArrayList<DimensionModel> list2 = new ArrayList<DimensionModel>();
		list2.add(model1);
		list2.add(model3);
		list2.add(model2);
		list2.add(model18);

/*		ArrayList<DimensionModel> list3 = new ArrayList<DimensionModel>();
		list3.add(model8);
		list3.add(model10);
		list3.add(model9);

		ArrayList<DimensionModel> list4 = new ArrayList<DimensionModel>();
		list4.add(model11);
		list4.add(model13);
		list4.add(model12);

		ArrayList<DimensionModel> list5 = new ArrayList<DimensionModel>();
		list5.add(model11);
		list5.add(model13);
		list5.add(model16);*/

		/*
		 * ArrayList<DimensionModel> list3 = new ArrayList<DimensionModel>();
		 * list1.add(model7); list1.add(model8);
		 * 
		 * ArrayList<DimensionModel> list4 = new ArrayList<DimensionModel>();
		 * list1.add(model9);
		 */

		rules = new ArrayList<String>();
		rules.add("PublicationGroup");
		rules.add("Campaign");
		rules.add("Publication");
		rules.add("MasterPublication");

		cube = new CubeRepository(groupRepository);
		groupRepository.save(new DimensionGroup("group01", "group1", list1));
		groupRepository.save(new DimensionGroup("group02", "group2", list2));
		/*
		 * groupRepository.save(new DimensionGroup("group03", "group3", list3));
		 * groupRepository.save(new DimensionGroup("group04", "group4", list4));
		 * groupRepository.save(new DimensionGroup("group05", "group5", list5));
		 */
		/*
		 * groupRepository.save(new DimensionGroup("group03", "group3", list3));
		 * groupRepository.save(new DimensionGroup("group04", "group4", list4));
		 */

	}

	@Test
	public void itShouldFetchAllDimensions() {
		// given

		// when

		List<DimensionGroup> dimensionGroups = cube.fetchAllGroups();

		// then

		System.out.println("total"+dimensionGroups.size());
		Assert.assertNotNull(dimensionGroups);
	}

	@Test
	public void itShouldApplyRuleToEveryGroup() {
		// given

		// when

		List<DimensionGroup> dimensionGroups = cube.fetchAllGroups();
		JSONArray objectsOnDiffGroups = cube.applyRule(rules, dimensionGroups);
		JSONArray array = cube.getTree();
		// then

		System.out.println("Final Array" + array);
		Assert.assertEquals(5, objectsOnDiffGroups.size());
		Assert.assertNotNull(objectsOnDiffGroups);
	}

	/*
	 * @Test public void itShouldGroupAllPaths() {
	 * 
	 * // given
	 * 
	 * // when List<DimensionGroup> dimensionGroups = cube.fetchAllGroups();
	 * JSONArray singlePaths = cube.applyRule(rules, dimensionGroups); JSONArray
	 * array = cube.getTree();
	 * 
	 * // then
	 * 
	 * }
	 */
}
