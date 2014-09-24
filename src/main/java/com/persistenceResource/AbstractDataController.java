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
	public ArrayList<String> getAllUsernames(EntityManager em);
}
