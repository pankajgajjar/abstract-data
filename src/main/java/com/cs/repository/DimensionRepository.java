package com.cs.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cs.data.core.IRepository;
import com.cs.model.DimensionGroup;
import com.cs.model.DimensionModel;

public class DimensionRepository {

	private DimensionGroup dimensionGroup;

	@Autowired
	public DimensionRepository(DimensionGroup dimensionGroup) {

		this.dimensionGroup = dimensionGroup;
	}

	public String createDimension(DimensionModel dimension) {
		
		DimensionGroup dimensionGroup = getDimensionGroupOf(dimension);
		if (dimensionGroup.exists()) {
			List<DimensionModel> dimensionModels = dimensionGroup
					.getDimensions();
			dimensionModels.add(dimension);
			dimensionGroup.setDimensions(dimensionModels);

		} else {
			List<DimensionModel> dimensions = new ArrayList<DimensionModel>();
			dimensions.add(dimension);
			dimensionGroup.setDimensions(dimensions);
			dimensionGroup.toggleExistence(true);

		}
		return dimension.getId();
	}

	private DimensionGroup getDimensionGroupOf(DimensionModel dimension) {
		// TODO Auto-generated method stub
		return null;
	}

}
