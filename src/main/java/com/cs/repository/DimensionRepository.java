package com.cs.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.cs.data.core.IRepository;
import com.cs.model.DimensionModel;

public class DimensionRepository {

	private IRepository redis;

	@Autowired
	public DimensionRepository(IRepository iRepository) {
		this.redis = iRepository;
	}
	
	public void create(DimensionModel dimensionModel){
		
	}

}
