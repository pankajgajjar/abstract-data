package com.cs.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.cache.ViewStructureCache;
import com.cs.data.core.nosql.mongodb.MongoRepository;
import com.cs.model.ContentObject;

@Component
public class ChapterRepository {

	private MongoRepository nosqlTemplateForMongo;
	private ViewStructureCache cache;
	private final String COMMA = ",";
	private final String HIPHEN = "-";

	@Autowired
	public ChapterRepository(MongoRepository nosqlTemplateForMongo,
			ViewStructureCache cache) {
		this.nosqlTemplateForMongo = nosqlTemplateForMongo;
		this.cache = cache;

	}

	public String save(ContentObject chapter) {

		ContentObject publication = getParentPublication(chapter.getPath());
		addChapterToPublication(publication, chapter);

		return nosqlTemplateForMongo.save(chapter);
	}

	private void addChapterToPublication(ContentObject publication,
			ContentObject chapter) {
		System.out.println(getParentId(chapter.getPath()));
		ContentObject parent;
		parent = find(publication, getParentId(chapter.getPath()));
		parent.addchild(chapter);
		saveToMongo(publication);

	}

	private void saveToMongo(ContentObject publication) {
		nosqlTemplateForMongo.save(publication);
	}

	protected ContentObject find(ContentObject publication, String parentId) {
		ContentObject child = null;
		if (publication.getId().equals(parentId)) {
			return publication;

		}

		if (publication.hasChildren()) {
			for (ContentObject chapter : publication.getChildren()) {
				if (child != null)
					break;
				if (chapter.getId().equals(parentId)) {
					return chapter;

				} else {
					child = find(chapter, parentId);

				}

			}
		}
		return child;
	}

	protected String getPublicationId(String path) {

		String currentViewStructure = cache.getCurrentViewStructure();
		System.out.println(currentViewStructure);
		System.out.println(path);
		int lastIndex = getLastIndexOf(currentViewStructure);

		return path.split(COMMA)[lastIndex];

	}

	protected int getLastIndexOf(String currentViewStructure) {
		// TODO Auto-generated method stub
		return currentViewStructure.split(HIPHEN).length - 1;

	}

	protected String getParentId(String path) {
		String[] paths = path.split(COMMA);
		return paths[paths.length - 1];
	}

	protected ContentObject getParentPublication(String path) {
		return nosqlTemplateForMongo.getObjectByKey(getPublicationId(path),
				ContentObject.class);
	}

	public String delete(ContentObject chapter, String oldPath) {
		ContentObject parentPublication = getParentPublication(oldPath);
		ContentObject parent = find(parentPublication, getParentId(oldPath));
		chapter.setPath(oldPath);
		parent.removeChild(chapter);
		saveToMongo(parentPublication);
		return chapter.getId();
	}

}
