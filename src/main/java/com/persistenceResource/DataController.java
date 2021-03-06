package com.persistenceResource;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import com.entities.Item;
import com.entities.Message;
import com.entities.Profile;
import com.entities.Sale;
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
	public String getUsernames(EntityManager em, String namedQuery) throws Exception {

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
	 * @return A JSON formatted string of the messages for the given user.
	 */
	public String getMessages(EntityManager em, String username) throws Exception {

		try {
			Query query = em.createNamedQuery("getMessages").setParameter("username", username);
			List<Object[]> rawMessages = query.getResultList();

				return ObjectMapper.mapMessages(rawMessages).toString();
			

		} catch (Exception e) {
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
		Profile prof = new Profile();
		prof.setBio("");
		prof.setProfilePicture("profilePics/defaultPhoto.jpg");
		
		prof.setReputation(0);
		prof.setUsername(username);
		em.persist(user);
		em.persist(prof);
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
		em.getTransaction().begin();
		Message messageToSend = new Message();
		messageToSend.setMessageID(SecureIDGenerator.nextSecureId());
		messageToSend.setUsername(username);
		messageToSend.setSender(sender);
		messageToSend.setMessage(message);
		messageToSend.setDateOpened(null);
		messageToSend.setDateSent(new Date());
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
	 * @return	A JSON status message on whether or not the login credentials are correct.
	 */
	public String userLogin(EntityManager em, String username, String password) throws Exception {
		Query query = em.createNamedQuery("userLogin").setParameter("username", username).setParameter("password", password);
		if (query.getResultList().size() == 0) {
			return "{\"Result\":[{\"Status\":\"Login Failure\"}]}";
		} else {
			return "{\"Result\":[{\"Status\":\"Login Success\"}]}";
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

			throw new Exception();

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
	
	/**
	 * This method is used to get users with a given type (Basic, Moderator, Admin).
	 * 
	 * @param em	Our instance of EntityManager used for persistence.
	 * @param userType	The type of user to be searched for (Basic, Moderator, Admin)
	 * 
	 * @return A JSON string of all usernames with the given user type.
	 * @throws Exception
	 */
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
			int aveSize = coordinateList.size();
			for(Point2D.Double coordinate : coordinateList) {
				if(coordinate.getY() != 0.0 && coordinate.getX() != 0.0) {
					averageLatitude += coordinate.getY();
					averageLongitude += coordinate.getX();
				}
				else {
					aveSize--;
				}
			}
			averageLatitude = averageLatitude/aveSize;
			averageLongitude = averageLongitude/aveSize;
			
			averageLocation.setLocation(averageLongitude, averageLatitude);
			return averageLocation;
			
		} catch(Exception e) {
			throw e;
		}
	
	}
	
	/**
	 * This method is used when an AJAX call is received to check if the logged in user
	 * has the proper credentials. The HttpServletRequest is used to grab an authorization
	 * header that is to contain the username and password of the logged in user.
	 * 
	 * @param em	Our instance of EntityManager used for persistence.
	 * @param req	The HttpServletRequest containing the username and password in an authorization header.
	 * 
	 * @return	True of False based on whether or not the credentials provided were legitimate.
	 */
	public boolean verifyCredentials(EntityManager em, HttpServletRequest req) {
		
		String rawCredentials = req.getHeader("Authorization");
		if(rawCredentials == null) {
			return false;
		}
    	String[] credentials = rawCredentials.split(":");
    	if(credentials.length == 1) {
    		return false;
    	}
    	String username = credentials[0];
    	String password = credentials[1];
    	
    	try {
    		if(this.userLogin(em, username, password).equals("{\"Result\":[{\"Status\":\"Login Failure\"}]}")) {
    			em.close();
    			return false;
    		}
    	} catch (Exception e) {
    		em.close();
    		return false;
    	}
    	
    	return true;
    	
	}
	
	/**
	 * This method is used to get the profile picture location of the user.
	 * 
	 * @param em Our instance of EntityManager used for persistence.
	 * @param username The username of the user we want to get the picture for.
	 * @return A String that indicates the location of the profile picture.
	 * @throws Exception
	 */
	public String getProfPic(EntityManager em, String username) throws Exception {
		
		try {
			
			Query q = em.createNamedQuery("getProfPic").setParameter("username", username);
			if(q.getResultList().size() == 0) {
			
				em.close();
				return "none";
			
			} else {
	
				String out = (String) q.getResultList().get(0);
				em.close();
				return out;
			
			}
			
		} catch (Exception e) {
			em.close();
			throw e;	
		}
		
	}
	
	/**
	 * This method is used to get the bio of the given user.
	 * 
	 * @param em Our instance of EntityManager used for persistence.
	 * @param username The username of the user we want to get the bio of.
	 * 
	 * @return A String containing the bio.
	 * @throws Exception
	 */
	public String getBio(EntityManager em, String username) throws Exception {
		
		try {
			
			Query q = em.createNamedQuery("getBio").setParameter("username", username);
			if (q.getResultList().size() == 0) {
				
				em.close();
				return "none";
				
			} else {
				
				String out = (String) q.getResultList().get(0);
				em.close();
				return out;
				
			}
			
		} catch (Exception e) {
			
			em.close();
			throw e;
			
		}
		
	}
	
	/**
	 * This method is used to get the current status of the given user.
	 * 
	 * @param em Our instance of EntityManager used for persistence.
	 * @param username The username of the user we want to get the status of.
	 * 
	 * @return A String containing the status.
	 * @throws Exception
	 */
	public String getStatus(EntityManager em, String username) throws Exception {
		
		try {
			
			Query q = em.createNamedQuery("getStatus").setParameter("username", username);
			if (q.getResultList().size() == 0) {
				
				em.close();
				return "none";
				
			} else {
				
				String out = (String) q.getResultList().get(0);
				em.close();
				return out;
				
			}
			
		} catch (Exception e) {
			
			em.close();
			throw e;
			
		}
		
	}
	
	/**
	 * This method is used to get the current number of trades the given user
	 *  has participated in.
	 * 
	 * @param em Our instance of EntityManager used for persistence.
	 * @param username The username of the user we want to get the trade count of.
	 * 
	 * @return A String containing the number of trades participated in by username.
	 * @throws Exception
	 */
	public String getTotTrades(EntityManager em, String username) throws Exception {
		
		try {
			
			Query q = em.createNamedQuery("getTotTrades").setParameter("username", username);
			if (q.getResultList().size() == 0) {
				
				em.close();
				return "none";
				
			} else {
				
				Long outNumber = (Long) q.getResultList().get(0);
				em.close();
				String out = outNumber.toString();
				return out;
				
			}
			
		} catch (Exception e) {
			em.close();
			throw e;
			
		}
		
	}
	
	/**
	 * Gets the number of open sales that a user has.
	 * @param em Instance of EntityManager used for persistence.
	 * @param username The username that we want to get unfinished/open sales of. 
	 * @return The number of open sales a user has.
	 * @throws Exception
	 */
	public long getOpenSales(EntityManager em, String username) throws Exception {
		
		try {
			
			Query q = em.createNamedQuery("getOpenSales").setParameter("username", username);
			long size = (Long) q.getResultList().get(0);
			em.close();
			return size;

		} catch (Exception e) {
			
			em.close();
			throw e;
			
		}
		
	}
	
	/**
	 * This method is used to analyze different aspects of messages such
	 * as average length and frequency sent.
	 * 
	 * @param	em Our instance of EntityManager used for persistence.
	 * 
	 * @return	A JSON string containing various pieces of information regarding messages
	 * @throws Exception
	 */
	public double getMessageData(EntityManager em) throws Exception	{
		
		double averageLength = 0.0;
		try {
			Query query = em.createNamedQuery("getAllMessages");
			List<Object> rawMessages =  query.getResultList();
			
			for(Object message : rawMessages) {	
				String tempMessageContents = (String) message;
				averageLength += tempMessageContents.length();
			}
			
			averageLength = averageLength/rawMessages.size();
			
		} catch (Exception e) {

			throw e;
			
		}
		
		return averageLength;
	}
	
	
	/**
	 * This method is used to get the current open sales
	 * 
	 * @param em Our instance of EntityManager used for persistence.
	 * 
	 * @return A JSON String containing the open sales and their data
	 */
	public String getCurrentSales(EntityManager em) {
		
		Query query = em.createNamedQuery("getCurrentSales");
		List<Object[]> rawSales = query.getResultList();
		return ObjectMapper.mapCurrentSales(rawSales);
		
	}

	
	/**
	 * This method is used to get the current open sales
	 * 
	 * @param em Our instance of EntityManager used for persistence.
	 * @param req	An HttpServletRequest containing header data used for authentication
	 * @param description	The description of the sale
	 * @param tags	The tags associated with the sale (for searches)
	 * 
	 * @return A JSON String indicating success or failure of the operation
	 * @throws Exception
	 */
	public String createSale(EntityManager em, HttpServletRequest req, String description, String tags) throws Exception {
    	
		String[] credentials = req.getHeader("Authorization").split(":");
    	if(credentials.length == 1) {
    		throw new Exception();
    	}
    	String primarySeller = credentials[0];
		
		em.getTransaction().begin();
		Sale saleToCreate = new Sale();
		saleToCreate.setSaleID(SecureIDGenerator.nextSecureId());
		saleToCreate.setPrimarySeller(primarySeller);
		saleToCreate.setSaleDescription(description);
		saleToCreate.setTags(tags);
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date currentDate = new java.sql.Date(utilDate.getTime());
		saleToCreate.setDateCreated(currentDate);
		em.persist(saleToCreate);
		em.getTransaction().commit();
		em.close();
		return "Success";

	}
	
	/**
	 * This method is used to get the user type of a user by their authentication header
	 * 
	 * @param em Our instance of EntityManager used for persistence.
	 * @param req	An HttpServletRequest containing header data used for authentication
	 * 
	 * @return A JSON String indicating the user type of the user
	 */
	public String getUserType(EntityManager em, HttpServletRequest req) {
    	String[] credentials = req.getHeader("Authorization").split(":");
    	if(credentials.length == 1) {
    		return "{\"Result\":\"Verification Failure\"}";
    	}
    	String username = credentials[0];
    	
    	try {
    		Query query = em.createNamedQuery("getUserType").setParameter("username", username);
    		List<String> rawType = query.getResultList();
    		if(rawType.size() == 0) {
    			return "{\"Result\":\"Verification Failure\"}";
    		}
    		else {
    			return "{\"Result\":\"" + rawType.get(0) + "\"}";
    		}
    	}
    	catch (Exception e) {
    		return "{\"Result\":\"Exception Failure\"}";
    	}
	}
	
	/**
	 * This method is used to update the given users profile bio
	 * 
	 * @param em Our instance of EntityManager used for persistence.
	 * @param username	The username of the user who's bio will be updated
	 * @param bio	The new bio to be updated
	 * 
	 * @return A boolean indicating whether or not the operation was a success
	 * @throws Exception
	 */
	public boolean updateProfile(EntityManager em, String username, String bio) throws Exception {
		
		try {
			
			Profile p = em.find(Profile.class, username);
			em.getTransaction().begin();
			p.setBio(bio);
			em.getTransaction().commit();
			em.close();
			return true;
			
		} catch (Exception e) {
			
			throw e;
			
		}
		
	}
	
	/**
	 * This method is used to update the given users profile picture
	 * 
	 * @param em Our instance of EntityManager used for persistence.
	 * @param username	The username of the user who's picture will be updated
	 * @param pic	The URL of the photo to be used
	 * 
	 * @return A boolean indicating whether or not the operation was a success
	 * @throws Exception
	 */
	public boolean updateProfilePic(EntityManager em, String username, String pic) throws Exception {
		
		try {
			Profile p = em.find(Profile.class, username);
			em.getTransaction().begin();
			p.setProfilePicture(pic);
			em.getTransaction().commit();
			em.close();
			return true;
			
		} catch (Exception e) {
			
			throw e;
			
		}
		
	}
	
	/**
	 * This method is used to update the given users profile bio
	 * 
	 * @param em Our instance of EntityManager used for persistence.
	 * @param itemName	The name of the item to be created.
	 * @param saleID	The unique saleID of the sale in which the item was created from.
	 * 
	 * @return A String indicating whether or not the operation was a success
	 * @throws Exception
	 */
	public String createItem(EntityManager em, String username, String itemName, String saleID) throws Exception {
    	
    	EntityTransaction et = em.getTransaction();
    	et.begin();
    	Item i = new Item();
    	i.setItemID(SecureIDGenerator.nextSecureId());
    	i.setUsername(username);
    	i.setItemName(itemName);
    	i.setSaleID(saleID);
    	em.persist(i);
    	et.commit();
    	em.close();
    	return "Success";
		
	}
	
	/**
	 * This method is used to retrieve all items of a given user
	 * 
	 * @param em Our instance of EntityManager used for persistence.
	 * @param username	The username of the user who's bio items are to be retrieved
	 * 
	 * @return A JSON String containing the items and their details.
	 * @throws Exception
	 */
	public String getUsersItems(EntityManager em, String username) throws Exception {
		
		try {
			
			Query q = em.createNamedQuery("getUsersItems").setParameter("username", username);
			return ObjectMapper.mapUsersItems(q.getResultList());
			
		} catch (Exception e) {
			
			throw e;
			
		}
		
	}
	
	/**
	 * This method is used to trade an item
	 * 
	 * @param em Our instance of EntityManager used for persistence.
	 * @param itemID	The unique itemID of the item being traded
	 * @param saleID	The unique saleID in which the item is being traded
	 * 
	 * @return A String indicating whether or not the operation was a success
	 * @throws Exception
	 */
	@Deprecated
	public String tradeItem(EntityManager em, String itemID, String saleID) throws Exception {
		
		try {

			Item i = (Item) em.find(Item.class, itemID);
			em.getTransaction().begin();
			i.setSaleID(saleID);
			em.getTransaction().commit();
			em.close();
			return "Success";
			
		} catch (Exception e) {
			
			throw e;
			
		}
		
	}
	
	
	
	
	/**
	 * This method is used to get the reputation of a given user
	 * 
	 * @param em Our instance of EntityManager used for persistence.
	 * @param username	The username of the user who's reputation will be retrieved
	 * 
	 * @return An integer indicating the given user's reputation
	 * @throws Exception
	 */
	public String getReputation(EntityManager em, String username) throws Exception {
		
		try {
			
			Query q = em.createNamedQuery("getReputation").setParameter("username", username);
			List rawRep = q.getResultList();
			return Integer.toString((Integer) rawRep.get(0));
			
		} catch (Exception e) {
			
			throw e;
			
		}
		
	}
	
	/**
	 * This method is used to get the username of the primarySeller of a sale
	 * 
	 * @param em Our instance of EntityManager used for persistence.
	 * @param saleID	The unique saleID of the sale
	 * 
	 * @return A String containing the username of the primarySeller of the sale
	 * @throws Exception
	 */
	public String getPrimarySeller(EntityManager em, String saleID) throws Exception {
		
		try {
			
			Query q = em.createNamedQuery("getPrimarySeller").setParameter("saleID", saleID);
			List rawSeller = q.getResultList();
			return (String) rawSeller.get(0);
			
		} catch (Exception e) {
			
			throw e;
			
		}
		
	}
	
	
	/**
	 * This method is used to update the data associated with the given saleID
	 * 
	 * @param em Our instance of EntityManager used for persistence.
	 * @param saleID	The unique saleID of the sale
	 * @param lat	The latitude of the chosen location
	 * @param lon	The longitude of the chosen location
	 * @param primLocation	The coordinates chosen by the primarySeller
	 * @param secLocation	The coordinates chosen by the secondarySeller
	 * 
	 * @return A boolean indicating whether or not the operation was a success
	 * @throws Exception
	 */
	public boolean setSaleData(EntityManager em, String saleID, String lat, String lon, String primLocation, String secLocation, String secSeller) throws Exception {
		
		try {
			
			Sale s = em.find(Sale.class, saleID);
			em.getTransaction().begin();
			s.setLatitude(Double.parseDouble(lat));
			s.setLongitude(Double.parseDouble(lon));
			s.setPrimarySellerLocation(primLocation);
			s.setSecondarySellerLocation(secLocation);
			s.setSecondarySeller(secSeller);
			em.getTransaction().commit();
			em.close();
			return true;
			
		} catch (Exception e) {
			
			throw e;
			
		}
		
	}

	/**
	 * This method is used to update the secondarySeller of a given sale based on saleID
	 * 
	 * @param em Our instance of EntityManager used for persistence.
	 * @param saleID	The unique saleID of the sale
	 * @param secSeller	The username of the secondarySeller for the sale
	 * 
	 * @return A boolean indicating whether or not the operation was a success
	 * @throws Exception
	 */
	public boolean setSaleSecondarySeller(EntityManager em, String saleID, String secSeller) throws Exception {
		
		try {
			
			Sale s = em.find(Sale.class, saleID);
			em.getTransaction().begin();
			s.setSecondarySeller(secSeller);
			em.getTransaction().commit();
			em.close();
			return true;
			
		} catch (Exception e) {
			
			throw e;
			
		}
		
	}
	
	
	
	/**
	 * This method is used to retrieve sale tags associated with a given saleID
	 * 
	 * @param em Our instance of EntityManager used for persistence.
	 * @param saleID	The unique saleID of the sale
	 * 
	 * @return A String containing raw tag data
	 * @throws Exception
	 */
	public String getSaleTags(EntityManager em, String saleID) throws Exception {
		
		try {
			
			Query q = em.createNamedQuery("getSaleTags").setParameter("saleID", saleID);
			List<String> result = q.getResultList();
			int success = result.size();
			
			if (success == 0) {

				em.close();
				return null;
				
			} else {

				em.close();
				return result.get(0);
				
			}

		} catch (Exception e) {
			
			em.close();
			throw e;
			
		}
		
	}

	/**
	 * This method is used to retrieve the coordinates of all sales and place them in JSON format
	 * 
	 * @param em Our instance of EntityManager used for persistence.
	 * 
	 * @return A JSON String containing all sale coordinates (except for incomplete sales)
	 * @throws Exception
	 */
	public String getAllSaleLocations(EntityManager em) throws Exception {
		
		try{
			Query query = em.createNamedQuery("getAllCoordinates");
			ArrayList<Point2D.Double> coordinateList = ObjectMapper.mapCoordinates(query.getResultList());
			
			String allCoordinates = "{\"Result\":[{\"Status\":\"Success\"}, {\"Locations\":[";
			for(Point2D.Double coordinate : coordinateList) {
				String lat = Double.toString(coordinate.getY());
				String lon = Double.toString(coordinate.getX());
				if(!lat.equals("0.0") && !lon.equals("0.0")) {
					String tempCoord = "{\"Lat\":\"" + lat + "\",\"Lon\":\"" + lon + "\"},";
					allCoordinates += tempCoord;
				}
			}
			if(coordinateList.size() > 0) {
				allCoordinates = allCoordinates.substring(0, allCoordinates.length()-1);
			}
			allCoordinates += "]}]}";
			
			return allCoordinates;
		}
		catch(Exception e) {
			throw e;
		}
		
	}
	
}
