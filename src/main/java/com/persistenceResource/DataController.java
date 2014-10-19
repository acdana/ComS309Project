package com.persistenceResource;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;

import com.entities.Message;
import com.entities.User;
import com.objectManagement.ObjectMapper;

/**
 * This class is the implementation of AbstractDataRetreival. We use our
 * generated EntityManager to call queries that have been created in our
 * Entities (com.entities package).
 * 
 * @author Alex
 *
 */
@SuppressWarnings("unchecked")
public class DataController implements AbstractDataController {

	/**
	 * This method is used to process raw data retrieved from queries that will
	 * be a list of user names. The entity manager and query to process must be
	 * supplied.
	 * 
	 * @param em	Our instance of EntityManager used for persistence.
	 * @param namedQuery	The named query that we wish to process. Must be defined in
	 *            			some entity class.
	 *            
	 * @throws Exception
	 * @return An ArrayList of Strings representing every user that is retrieved
	 *         using namedQuery.
	 */
	public ArrayList<String> getUsernames(EntityManager em, String namedQuery) throws Exception {

		Query query = em.createNamedQuery(namedQuery);
		try {
			if (query.getResultList().size() == 0) {
				return null;
			}
			return ObjectMapper.mapUsernames(query.getResultList());
		} catch (Exception e) {
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
			if (deleted == 0)
				return "Failure";
			else
				return "Success";
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * This method is called to penalize a user. The user name of the user to
	 * penalize is provided and the named query "penalizeUser" is called with
	 * the user name as the parameter. The query updated a users current penalty
	 * count (int) by one.
	 * 
	 * @param em	Our instance of EntityManager used for persistence.
	 * @param usernameToPenalize	The user name of the user we wish to penalize
	 * 
	 * @throws Exception
	 * @return A status message indicating whether or not the user was penalized
	 *         successfully.
	 */
	public String penalizeUser(EntityManager em, String usernameToPenalize) throws Exception {

		try {

			Query q = em.createNamedQuery("penalizeUser").setParameter("usernameToPenalize", usernameToPenalize);
			int update = q.executeUpdate();

			if (update == 0) {
				return "Failure";
			} else
				return "Success";

		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * This method checks a given user names current number of penalties by
	 * calling the named query "getPenaltyCount" from the User entity with
	 * "usernameToCheck" as the parameter.
	 * 
	 * @param em   Our instance of EntityManager used for persistence.
	 * @param usernameToCheck	The user name of the user we wish to get the current penalty
	 *            				count of.
	 * 
	 * @throws Exception
	 * @return A status message indicating whether or not the user was checked
	 *         successfully.
	 */
	public String getPenaltyCount(EntityManager em, String usernameToCheck) throws Exception {

		try {

			Query q = em.createNamedQuery("getPenaltyCount").setParameter("usernameToCheck", usernameToCheck);

			if (q.getResultList().size() == 0) {
				return "Could not find user.";
			} else {
				return q.getResultList().get(0).toString();
			}

		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * This method is used to retrieve current messages of a given user.
	 * Messages are retrieved using the named quuery "getMessages" with
	 * "username" as a parameter.
	 * 
	 * @param em	Our instance of EntityManager used for persistence.      
	 * @param username	The user name of the user we wish to retrieve messages for.
	 *            
	 * 
	 * @throws Exception
	 * @return A status message indicating whether or not the user's messages
	 *         were retrieved successfully.
	 */
	public String getMessages(EntityManager em, String username) throws Exception {

		try {
			Query query = em.createNamedQuery("getMessages").setParameter("username", username);
			List<Object[]> rawMessages = query.getResultList();
			if (rawMessages.size() == 0) {
				return "No messages";
			} else {
				return ObjectMapper.mapMessages(rawMessages).toString();
			}

		} catch (Exception e) {
			System.out.println("Here");
			throw e;
		}
	}
	
	/**
	 * This method is used to create a new user based on a username, password, and email.
	 * 
	 * @param em	Our instance of EntityManager used for persistence.
	 * @param username	The username that will be added to the database.
	 * @param password	The password that will be associated with the given username.
	 * @param email		The email that will be associated with the given username.
	 */
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
	
	/**
	 * This method is used to create a new message that can be sent to another user.
	 * 
	 * @param em	Our instance of EntityManager used for persistence.
	 * @param username	The username of the user that the message is to be sent to.
	 * @param message	The contents of the message to be sent.
	 * @param sender	The username of the user that is sending the message.
	 * 
	 * @throws Exception
	 */
	public void createNewMessage(EntityManager em, String username, String message, String sender) throws Exception {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		em.getTransaction().begin();
		Message messageToSend = new Message();
		messageToSend.setMessageID(SecureIDGenerator.nextSecureId());
		messageToSend.setUsername(username);
		messageToSend.setSender(sender);
		messageToSend.setMessage(message);
		messageToSend.setDateOpened(null);
		messageToSend.setDateSent(cb.currentTimestamp());
		em.persist(messageToSend);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * This method is used to check for a matching username and password on login.
	 * 
	 * @param em	Our instance of EntityManager used for persistence.
	 * @param username	The username of the user that is attempting to login
	 * @param passowrd	The password used to attempt to login.
	 * 
	 * @return	A status message on whether or not the login credentials are correct.
	 */
	public String userLogin(EntityManager em, String username, String password) throws Exception {
		Query query = em.createNamedQuery("userLogin").setParameter("username", username).setParameter("password", password);
		if (query.getResultList().size() == 0) {
			return "Failed";
		} else {
			return "Success";
		}
	}

	/**
	 * This method is used to retrieve a current user's type.
	 * 
	 * @param em	Our instance of EntityManager used for persistence.
	 * @param username	The username of the user that will have the status update.
	 * 
	 * @return The type of user that the given username is registered as.
	 * @throws Exception
	 */
	public String getUserType(EntityManager em, String username) throws Exception {

		Query q = em.createNamedQuery("getUserType").setParameter("username", username);
		if (q.getResultList().size() == 0) {

			return "None";

		} else {

			return q.getResultList().get(0).toString();

		}

	}

	/**
	 * This method is used to update a current user's type between Basic, Moderator, and Admin.
	 * 
	 * @param em	Our instance of EntityManager used for persistence.
	 * @param username	The username of the user that will have the status update.
	 * @param userType	The type of user that will be assigned to the given username (Basic, Moderator, Admin)
	 * 
	 * @return A status message regarding whether or not the update was successful.
	 * @throws Exception
	 */
	public String changeUserType(EntityManager em, String username, String userType) throws Exception {

		try {

			Query q = em.createNamedQuery("make" + userType).setParameter("username", username);
			if (q.executeUpdate() == 1) {

				return "Success";

			} else {

				return "Failure";

			}

		} catch (Exception e) {

			throw e;

		}

	}
	
	public String getUsers(EntityManager em, String userType) throws Exception {
		
		try {
			
			Query q = em.createNamedQuery("getUsers").setParameter("userType", userType);
			if (q.getResultList().size() == 0) {
				
				return "{\"Status\":\"Failure\"}";
				
			} else {
				
				return ObjectMapper.mapUsernames(q.getResultList()).toString();
				
			}
			
		} catch (Exception e) {
			
			em.close();
			throw e;
			
		}
		
	}
	
	/**
	 * This method will retrieve all sale coordinates and then average them out
	 * so that we get an average sale location.
	 * 
	 * @param em	Our instance of EntityManager used for persistence.
	 * 
	 * @return	The average location of all sales represented as a Point2D.Double coordinate.
	 * @throws Exception
	 */
	public Point2D.Double getAverageSaleLocation(EntityManager em) throws Exception {
		
		try{
			Query query = em.createNamedQuery("getAllCoordinates");
			ArrayList<Point2D.Double> coordinateList = ObjectMapper.mapCoordinates(query.getResultList());
			Point2D.Double averageLocation = new Point2D.Double();
			double averageLatitude = 0.0;
			double averageLongitude = 0.0;
			int setSize = coordinateList.size();
			for(Point2D.Double coordinate : coordinateList) {
				averageLatitude += coordinate.getY();
				averageLongitude += coordinate.getX();
			}
			averageLatitude = averageLatitude/setSize;
			averageLongitude = averageLongitude/setSize;
			
			averageLocation.setLocation(averageLongitude, averageLatitude);
			return averageLocation;
			
		} catch(Exception e) {
			throw e;
		}
	
	}
	
	public boolean verifyCredentials(EntityManager em, HttpServletRequest req) {
    	String[] credentials = req.getHeader("Authorization").split(":");
    	String username = credentials[0];
    	String password = credentials[1];
    	
    	try {
    		if(this.userLogin(em, username, password).equals("Failed")) {
    			em.close();
    			return false;
    		}
    	} catch (Exception e) {
    		em.close();
    		return false;
    	}
    	
    	return true;
	}
	

}
