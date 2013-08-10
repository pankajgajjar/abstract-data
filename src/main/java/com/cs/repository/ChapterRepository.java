package com.cs.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.cache.DimensionGroupCache;
import com.cs.cache.ViewStructureCache;
import com.cs.data.core.nosql.NoSqlOperations;
import com.cs.data.core.nosql.mongodb.MongoRepository;
import com.cs.model.ContentObject;
import com.cs.utils.ArrayUtils;
import com.sun.org.apache.regexp.internal.recompile;

@Component
public class ChapterRepository {

	private MongoRepository nosqlTemplateForMongo;
	private ViewStructureCache cache;

	@Autowired
	public ChapterRepository(MongoRepository nosqlTemplateForMongo,
			ViewStructureCache cache) {
		this.nosqlTemplateForMongo = nosqlTemplateForMongo;
		this.cache = cache;

	}

	public String save(ContentObject chapter) {

		ContentObject publication = nosqlTemplateForMongo.getObjectByKey(
				getPublicationId(chapter.getPath()), ContentObject.class);
		addChapterToPublication(publication, chapter);

		return nosqlTemplateForMongo.save(chapter);
	}

	private void addChapterToPublication(ContentObject publication,
			ContentObject chapter) {
		ContentObject publicationToUpdate = publication;
		publication = find(publication, chapter.getPath().split(",")[chapter
				.getPath().length() - 1]);
		publication.addchild(chapter);
		nosqlTemplateForMongo.save(publicationToUpdate);

	}

	private ContentObject find(ContentObject publication, String parentId) {
		if (publication.getId() == parentId) {
			return publication;

		} else {
			if (publication.hasChildren()) {

				for (ContentObject contentObject : publication.getChildren()) {
					if (publication.isPage()) {
						continue;

					}
					find(contentObject, parentId);

				}
			}

		}
		return publication;
	}

	protected String getPublicationId(String path) {

		String currentViewStructure = cache.getCurrentViewStructure();
		int lastIndex = getLastIndexOf(currentViewStructure);

		return path.split(",")[lastIndex];

	}

	protected int getLastIndexOf(String currentViewStructure) {
		// TODO Auto-generated method stub
		return currentViewStructure.split("-").length - 1;

	}

}
