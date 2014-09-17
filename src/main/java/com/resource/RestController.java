package com.resource;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/getAllUsernames")
public class RestController {
	EntityManager em = FactoryStartup.getAnEntityManager();
	DataController dataController = new DataController();
	
      @GET
      @Produces(MediaType.TEXT_PLAIN)
      public String getAllUsernames() {
    	  
    	ArrayList<String> userNames = dataController.getAllUsernames(em);
    	em.close();
        return userNames.toString();
     
      }


}
