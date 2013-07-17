package com.cs.data.core.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ORMFactory {

	public static EntityManager getEntityManagerForPersistence(
			final String persistenceUnitName) {
				return Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();

	}
}
