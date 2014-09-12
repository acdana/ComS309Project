package com.resource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryStartup {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("THIS_NEEDS_CHANGED");
	
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
