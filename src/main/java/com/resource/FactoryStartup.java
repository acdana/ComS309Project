package com.resource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * This class creates our EntityManager which is used for persistence throughout the running time of the application.
 * @author Alex
 *
 */
public class FactoryStartup {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ComS309Project");
	
	public EntityManager getOurEntityManager() {
		return emf.createEntityManager();
	}
   
	public void closeEntityManager(EntityManager em) {
		em.close();
	}
	
	public void closeEntityManagerFactory(EntityManagerFactory emf) {
		emf.close();
	}
	
	
   
}
