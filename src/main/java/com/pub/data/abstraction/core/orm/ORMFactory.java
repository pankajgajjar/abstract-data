package com.pub.data.abstraction.core.orm;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ORMFactory {

	public static EntityManager getEntityManagerForPersistence(
			final String persistenceUnitName) {
				return Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();

	}
}
