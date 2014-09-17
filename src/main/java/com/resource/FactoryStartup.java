package com.resource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;


/**
 * This class creates our EntityManager which is used for persistence throughout the running time of the application.
 * @author Alex
 *
 */

public class FactoryStartup {

	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ComS309Project");
	
	public static EntityManager getAnEntityManager() {
		return emf.createEntityManager();
	}
	
	public void closeEntityManagerFactory(EntityManagerFactory emf) {
		emf.close();
	}
	
	
   
}
