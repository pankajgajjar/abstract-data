package com.cs.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.model.ContentObject;
import com.cs.repository.ChapterRepository;

@Component
public class ChapterService implements IService {
	
	private ChapterRepository chapterRepository;

	@Autowired
	public ChapterService(ChapterRepository chapterRepository) {
		// TODO Auto-generated constructor stub
		this.chapterRepository=chapterRepository;
	}

	@Override
	public String getAll() throws IOException, URISyntaxException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String create(ContentObject chapter) {
		// TODO Auto-generated method stub
		return chapterRepository.save(chapter);
	}

	@Override
	public List<ContentObject> getAllBy(String structure) {
		// TODO Auto-generated method stub
		return null;
	}

}
