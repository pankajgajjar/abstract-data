package com.cs.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.model.ContentObject;
import com.cs.repository.ChapterRepository;

/**
 * The Class ChapterService.
 */
@Component
public class ChapterService implements IService {

	/** The chapter repository. */
	private ChapterRepository chapterRepository;

	/**
	 * Instantiates a new chapter service.
	 *
	 * @param chapterRepository the chapter repository
	 */
	@Autowired
	public ChapterService(ChapterRepository chapterRepository) {
		// TODO Auto-generated constructor stub
		this.chapterRepository = chapterRepository;
	}

	/* Get all chapters.
	 * @see com.cs.service.IService#getAll()
	 */
	@Override
	public String getAll() throws IOException, URISyntaxException {
		// TODO Auto-generated method stub
		return null;
	}

	/* Creates new chapter.
	 * @see com.cs.service.IService#create(com.cs.model.ContentObject)
	 */
	@Override
	public String create(ContentObject chapter) {
		return chapterRepository.save(chapter);
	}

	/* Get all chapters for given structure.
	 * @see com.cs.service.IService#getAllBy(java.lang.String)
	 */
	@Override
	public List<ContentObject> getAllBy(String structure) {
		return null;
	}

	/* Delets old chapter and creats new one.
	 * @see com.cs.service.IService#move(com.cs.model.ContentObject, java.lang.String)
	 */
	@Override
	public void move(ContentObject chapter, String path) {

		delete(chapter, path);
		create(chapter);

	}

	/* Delete chapter from given path.
	 * @see com.cs.service.IService#delete(com.cs.model.ContentObject, java.lang.String)
	 */
	@Override
	public void delete(ContentObject chapter, String path) {

		chapterRepository.delete(chapter, path);

	}

}
