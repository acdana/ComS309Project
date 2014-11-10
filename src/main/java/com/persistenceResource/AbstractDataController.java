package com.persistenceResource;

import java.awt.geom.Point2D;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 * This is the interface for all of the methods our REST calls will use. Anything we need to get from the database is done with
 * these methods.
 * @author Alex
 *
 */
public interface AbstractDataController {
	public void createNewUser(EntityManager em, String username, String password, String email) throws Exception;
	public String getUsernames(EntityManager em, String namedQuery) throws Exception;
	public String deleteUser(EntityManager em, String usernameToDelete) throws Exception;
	public String penalizeUser(EntityManager em, String usernameToPenalize) throws Exception;
	public String getPenaltyCount(EntityManager em, String usernameToCheck) throws Exception;
	public String getMessages(EntityManager em, String username) throws Exception;
	public void createNewMessage(EntityManager em, String username, String message, String sender) throws Exception;
	public String userLogin(EntityManager em, String username, String password) throws Exception;
	public String getUserType(EntityManager em, String username) throws Exception;
	public String changeUserType(EntityManager em, String username, String userType) throws Exception;
	public String getUsers(EntityManager em, String userType) throws Exception;
	public Point2D.Double getAverageSaleLocation(EntityManager em) throws Exception;
	public String getMessageData(EntityManager em) throws Exception;
	public String getTotTrades(EntityManager em, String username) throws Exception;
	public String getStatus(EntityManager em, String username) throws Exception;
	public String getBio(EntityManager em, String username) throws Exception;
	public String getProfPic(EntityManager em, String username) throws Exception;
	public boolean verifyCredentials(EntityManager em, HttpServletRequest req);
	public String getCurrentSales(EntityManager em) throws Exception;
	public String createSale(EntityManager em, HttpServletRequest req, String description) throws Exception;
}
