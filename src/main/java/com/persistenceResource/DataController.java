package com.persistenceResource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import com.entities.Message;
import com.entities.User;
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
	
	/**
	 * This method is used to process raw data retrieved from queries that will be a list of user names.
	 * The entity manager and query to process must be supplied.
	 * 
	 * @param em	Our instance of EntityManager used for persistence.
	 * @param namedQuery	The named query that we wish to process. Must be defined in some entity class.
	 * @throws Exception
	 * @return An ArrayList of Strings representing every user that is retrieved using namedQuery.
	 */
	public ArrayList<String> getUsernames(EntityManager em, String namedQuery) throws Exception {
		
		Query query = em.createNamedQuery(namedQuery);
		try {
			if(query.getResultList().size() == 0) {
				return null;
			}
			return ObjectMapper.mapUsernames(query.getResultList());
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * This method is called to delete a specified user from the database.
	 * Username and entity manager must be supplied.
	 * 
	 * @param em	Our instance of EntityManager used for persistence.
	 * @param usernameToDelete	The user name of the user to be deleted.
	 * 
	 * @throws Exception
	 * @return The status of the deletion, whether successful or not. 
	 */
	public String deleteUser(EntityManager em, String usernameToDelete) throws Exception {
		
		try {
			Query query = em.createNamedQuery("deleteUser").setParameter("usernameToDelete", usernameToDelete);
			int deleted = query.executeUpdate();
			if (deleted == 0) return "No entries deleted.";
			else return "Success";
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * This method is called to penalize a user. The user name of the user to penalize is provided
	 * and the named query "penalizeUser" is called with the user name as the parameter. The query
	 * updated a users current penalty count (int) by one.
	 * 
	 * @param em	Our instance of EntityManager used for persistence. 
	 * @param usernameToPenalize The user name of the user we wish to penalize
	 * 
	 * @throws Exception
	 * @return A status message indicating whether or not the user was penalized successfully.
	 */
	public String penalizeUser(EntityManager em, String usernameToPenalize) throws Exception {
		
		try {
			
			Query q = em.createNamedQuery("penalizeUser").setParameter("usernameToPenalize", usernameToPenalize);
			int update = q.executeUpdate();
			
			if (update == 0) {
				return "Did not penalize user: " + usernameToPenalize;
			}
			else return "Success";
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * This method checks a given user names current number of penalties by calling the
	 * named query "getPenaltyCount" from the User entity with "usernameToCheck" as the parameter.
	 * 
	 * @param em	Our instance of EntityManager used for persistence. 
	 * @param usernameToCheck The user name of the user we wish to get the current penalty count of.
	 * 
	 * @throws Exception
	 * @return A status message indicating whether or not the user was checked successfully.
	 */
	public String getPenaltyCount(EntityManager em, String usernameToCheck) throws Exception {
	
		try {
			
			Query q = em.createNamedQuery("getPenaltyCount").setParameter("usernameToCheck", usernameToCheck);
			
			if (q.getResultList().size() == 0) {
				return "Could not find user.";
			}
			else {
				return usernameToCheck + " has a penalty count of " + q.getResultList().get(0);
			}
			
		} catch (Exception e) {
			throw e;
		}
		
	}

	/**
	 * This method is used to retrieve current messages of a given user.
	 * Messages are retrieved using the named quuery "getMessages" with "username" as
	 * a parameter.
	 * 
	 * @param em	Our instance of EntityManager used for persistence. 
	 * @param username The user name of the user we wish to retrieve messages for.
	 * 
	 * @throws Exception
	 * @return A status message indicating whether or not the user's messages were retrieved successfully.
	 */
	public String getMessages(EntityManager em, String username) throws Exception {
		
		try {
			Query query = em.createNamedQuery("getMessages").setParameter("username", username);
			List<Object[]> rawMessages = query.getResultList();
			  System.out.println(SecureIDGenerator.nextSecureId());
			if(rawMessages.size() == 0) {
				return "No messages";
			}
			else {
				return ObjectMapper.mapMessages(rawMessages).toString();
			}
			
		} catch (Exception e) {
			System.out.println("Here");
			throw e;
		}
	}
	
	public void createNewUser(EntityManager em, String username, String password, String email) throws Exception {
		  em.getTransaction().begin();
		  User user = new User();
		  user.setUsername(username);
		  user.setPassword(password);
		  user.setEmail(email);
		  user.setAccountStatus("Clear");
		  user.setUserType("Basic");
		  user.setPenalties(0);
		  em.persist(user);
		  em.getTransaction().commit();
		  em.close();
	}
	
	public void createNewMessage(EntityManager em, String username, String message, String sender) throws Exception {
		  em.getTransaction().begin();
		  Message messageToSend = new Message();
		  messageToSend.setMessageID(SecureIDGenerator.nextSecureId());
		  messageToSend.setUsername(username);
		  messageToSend.setSender(sender);
		  messageToSend.setMessage(message);
		  messageToSend.setDateOpened(null);
		  messageToSend.setDateSent(null);
		  em.persist(messageToSend);
		  em.getTransaction().commit();
		  em.close();
	}

}
