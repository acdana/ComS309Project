package com.persistenceResource;

import java.util.ArrayList;

import javax.persistence.EntityManager;

/**
 * This is the interface for all of our REST calls. Anything we need to get from the database is done with
 * these methods.
 * @author Alex
 *
 */
public interface AbstractDataController {
	public ArrayList<String> getUsernames(EntityManager em, String namedQuery);
	public String deleteUser(EntityManager em, String usernameToDelete);
	public String penalizeUser(EntityManager em, String usernameToPenalize);
	public String getPenaltyCount(EntityManager em, String usernameToCheck);
}
