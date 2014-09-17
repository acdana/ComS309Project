package com.resource;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;
import javax.persistence.EntityManager;

/**
 * This class is the implementation of AbstractDataRetreival.
 * We use our generated EntityManager to call queries that have been created in
 * our Entities (com.entities package).
 * @author Alex
 *
 */
public class DataController implements AbstractDataController {
	
	public ArrayList<String> getAllUsernames(EntityManager em) {
		Query query = em.createNamedQuery("getAllUsernames");
		return ObjectMapper.mapAllUsernames(query.getResultList());
	}

}
