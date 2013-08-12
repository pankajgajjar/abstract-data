package com.cs.data.core.nosql.mongodb;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.cs.data.core.GenericDomain;
import com.cs.data.core.nosql.NoSqlOperations;
import com.cs.model.ContentObject;


/**
 * The Class MongoRepository.
 */
public class MongoRepository implements NoSqlOperations {

	/** The mongo template. */
	public MongoOperations mongoTemplate;

	/**
	 * Instantiates a new mongo repository.
	 *
	 * @param mongoTemplate the mongo template
	 */
	@Autowired
	public MongoRepository(MongoOperations mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/* Saves given object to configured mongoDb database.
	 * @see com.cs.data.core.nosql.NoSqlOperations#save(com.cs.data.core.GenericDomain)
	 */
	@Override
	public String save(GenericDomain objectToInsert) {
		mongoTemplate.save(objectToInsert);

		return "inserted";

	}

	/* Updates given object when configured mongoDb database.
	 * @see com.cs.data.core.nosql.NoSqlOperations#update(java.lang.Object)
	 */
	@Override
	public <T> T update(T query) {

		return null;
	}

	/* Deletes given object when configured mongoDb database.
	 * @see com.cs.data.core.nosql.NoSqlOperations#delete(java.lang.Object)
	 */
	@Override
	public <T> T delete(T objectToDelete) {
		return null;
	}

	/* Gets object by given object key.
	 * @see com.cs.data.core.nosql.NoSqlOperations#getObjectByKey(com.cs.data.core.GenericDomain, java.lang.Class)
	 */
	@Override
	public <P> P getObjectByKey(GenericDomain key, Class<P> type) {
		return mongoTemplate.findById(key.getKey(), type);
	}

	/* gets object by given hash key and object key by type.
	 * @see com.cs.data.core.nosql.NoSqlOperations#getObjectByKey(java.lang.String, java.lang.String, java.lang.Class)
	 */
	@Override
	public <P> P getObjectByKey(String key, String objectKey, Class<P> class1) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Update by id.
	 *
	 * @param <T> the generic type
	 * @param <P> the generic type
	 * @param id the id
	 * @param field the field
	 * @param valueToAdd the value to add
	 * @param type the type
	 */
	public <T, P> void updateById(String id, String field, P valueToAdd,
			Class<T> type) {

		mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(id)),
				new Update().push(field, valueToAdd), type);

	}

	/* Gets all documents of given collection.
	 * @see com.cs.data.core.nosql.NoSqlOperations#findAll(java.lang.Class)
	 */
	@Override
	public <T> List<T> findAll(Class<T> class1) {
		return mongoTemplate.findAll(class1);
	}

	/**
	 * Find.
	 *
	 * @param <T> the generic type
	 * @param key the key
	 * @param class1 the class1
	 * @return the t
	 */
	public <T> T find(String key, Class<T> class1) {

		return mongoTemplate.findById(key, class1);

	}

	/**
	 * Gets the objects by type.
	 *
	 * @param <T> the generic type
	 * @param field the field
	 * @param value the value
	 * @param type the type
	 * @return the objects by
	 */
	public <T> List<T> getObjectsBy(String field, String value, Class<T> type) {

		return mongoTemplate.find(Query.query(Criteria.where(field).is(value)),
				type);

	}

	/**
	 * Gets the object for and criteria.
	 *
	 * @param <T> the generic type
	 * @param <P> the generic type
	 * @param <Q> the generic type
	 * @param secondField the second field
	 * @param secondFieldValue the second field value
	 * @param firstField the first field
	 * @param firstFieldValue the first field value
	 * @param type the type
	 * @return the object for and criteria
	 */
	public <T, P, Q> List<Q> getObjectForAndCriteria(String secondField,
			P secondFieldValue, String firstField,
			Collection<T> firstFieldValue, Class<Q> type) {

		return mongoTemplate.find(
				Query.query(Criteria.where(firstField).in(firstFieldValue)
						.and(secondField).is(secondFieldValue)), type);

	}

	/**
	 * Gets the object by key.
	 *
	 * @param <T> the generic type
	 * @param id the id
	 * @param type the type
	 * @return the object by key
	 */
	public <T> T getObjectByKey(String id, Class<T> type) {
		// TODO Auto-generated method stub
		return mongoTemplate.findById(id, type);

	}
}
