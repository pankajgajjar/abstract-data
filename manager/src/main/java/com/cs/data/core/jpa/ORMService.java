package com.cs.data.core.jpa;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * The Class ORMService.
 */
public class ORMService {

	/** The entity manager. */
	private EntityManager entityManager;

	/**
	 * Instantiates a new oRM service.
	 */
	public ORMService() {

	}

	/**
	 * Instantiates a new oRM service.
	 *
	 * @param entityManager the entity manager
	 */
	@Autowired
	public ORMService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	

	/**
	 * Instantiates a new oRM service.
	 *
	 * @param ormTool the orm tool
	 */
	public ORMService(String ormTool) {
		this.entityManager = ORMFactory
				.getEntityManagerForPersistence("hibernate");
	}

	/**
	 * Insert.
	 *
	 * @param objectToSave the object to save
	 */
	public void insert(Object objectToSave) {
		// TODO Auto-generated method stub
		entityManager.persist(objectToSave);

	}

	/**
	 * Find by.
	 *
	 * @param <T> the generic type
	 * @param type the type
	 * @param id the id
	 * @return the object
	 */
	public <T> Object findBy(Class<T> type, Object id) {
		// TODO Auto-generated method stub
		return entityManager.find(type, id);
	}

}
