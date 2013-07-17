package com.cs.data.core.jpa;

import javax.persistence.EntityManager;

public class ORMService {

	private EntityManager entityManager;

	public ORMService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public ORMService(String ormTool){
		this.entityManager=ORMFactory.getEntityManagerForPersistence("hibernate");
	}

	public void save(Object objectToSave) {
		// TODO Auto-generated method stub
		entityManager.persist(objectToSave);

	}

	public <T> Object findBy(Class<T> type, Object id) {
		// TODO Auto-generated method stub
		return entityManager.find(type, id);
	}

}
