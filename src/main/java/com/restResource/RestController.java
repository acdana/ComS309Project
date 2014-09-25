package com.restResource;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.persistenceResource.DataController;
import com.persistenceResource.FactoryStartup;

@Path("/309")
public class RestController {
	EntityManager em = FactoryStartup.getAnEntityManager();
	DataController dataController = new DataController();
	
      @GET
      @Produces(MediaType.TEXT_PLAIN)
      @Path("/getAllUsernames")
      public String getAllUsernames() {
    	  
    	ArrayList<String> userNames = dataController.getAllUsernames(em);
    	em.close();
        return userNames.toString();
      }
      
     
      
      @GET
      @Produces(MediaType.TEXT_PLAIN)
      @Path("/getAllUsernames")
      public String createNewUser() {
    	  
    	//ArrayList<String> userNames = dataController.getAllUsernames(em);
    	em.close();
       // return userNames.toString();
     return "Test";
      }


}
