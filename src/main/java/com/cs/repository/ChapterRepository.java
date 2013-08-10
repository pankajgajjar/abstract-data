package com.cs.repository;

import org.springframework.stereotype.Component;

import com.cs.data.core.nosql.NoSqlOperations;
import com.cs.model.ContentObject;

@Component
public class ChapterRepository {

	private NoSqlOperations nosqlTemplateForMongo;

	public String save(ContentObject chapter) {

		return null;
	}

}
