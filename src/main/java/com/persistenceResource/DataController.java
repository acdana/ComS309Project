package com.persistenceResource;

import java.util.ArrayList;
import java.util.List;
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
	
	public ArrayList<String> getUsernames(EntityManager em, String namedQuery) {
		
		Query query = em.createNamedQuery(namedQuery);
		if(query.getResultList().size() == 0) {
			return null;
		}
		return ObjectMapper.mapUsernames(query.getResultList());
		
	}
	
	
	public String deleteUser(EntityManager em, String usernameToDelete) {
		
		try {
			Query query = em.createNamedQuery("deleteUser").setParameter("usernameToDelete", usernameToDelete);
			int deleted = query.executeUpdate();
			if (deleted == 0) return "No entries deleted.";
			else return "Success";
		}
		catch (Exception e) {
			return e.getMessage();
		}
		
	}
	
	
	public String penalizeUser(EntityManager em, String usernameToPenalize) {
		
		try {
			
			Query q = em.createNamedQuery("penalizeUser").setParameter("usernameToPenalize", usernameToPenalize);
			int update = q.executeUpdate();
			if (update == 0) return "Did not penalize user: " + usernameToPenalize;
			else return "Success";
			
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
	
	
	public String getPenaltyCount(EntityManager em, String usernameToCheck) {
	
		try {
			
			Query q = em.createNamedQuery("getPenaltyCount").setParameter("usernameToCheck", usernameToCheck);
			if (q.getResultList().size() == 0) return "Could not find user.";
			else {
				return usernameToCheck + " has a penalty count of " + q.getResultList().get(0);
			}
			
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}

	
	public String getMessages(EntityManager em, String username) {
		
		try {
			Query query = em.createNamedQuery("getMessages").setParameter("username", username);
			List<Object[]> rawMessages = query.getResultList();
			if(rawMessages.size() == 0) {
				return "No messages";
			}
			else {
				return ObjectMapper.mapMessages(rawMessages).toString();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "Failed to load messages";
		}
	}

}
