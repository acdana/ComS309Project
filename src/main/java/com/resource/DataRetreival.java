package com.resource;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;

/**
 * This class is the implementation of AbstractDataRetreival.
 * We use our generated EntityManager to call queries that have been created in
 * our Entities (com.entities package).
 * @author Alex
 *
 */
public class DataRetreival implements AbstractDataRetreival {

	public ArrayList<String> getAllUsernames(EntityManager em) {
		
		Query query = em.createNamedQuery("getAllUsernames");
		List<Object> rawUsernames = query.getResultList();
		return ObjectMapper.mapAllUsernames(rawUsernames);
		
	}

}
