package com.persistenceResource;

import java.util.ArrayList;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import com.objectManagement.ObjectMapper;

/**
 * This class is the implementation of AbstractDataRetreival.
 * We use our generated EntityManager to call queries that have been created in
 * our Entities (com.entities package).
 * @author Alex
 *
 */
@SuppressWarnings("unchecked")
public class DataController implements AbstractDataController {
	
	
	public ArrayList<String> getAllUsernames(EntityManager em) {
		
		Query query = em.createNamedQuery("getAllUsernames");
		if(query.getResultList().size() == 0) {
			return null;
		}
		return ObjectMapper.mapAllUsernames(query.getResultList());
		
		
	}

}
