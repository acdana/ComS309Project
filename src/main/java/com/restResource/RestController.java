package com.restResource;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.persistenceResource.DataController;
import com.persistenceResource.FactoryStartup;

@Path("/T11")
public class RestController {
	EntityManager em = FactoryStartup.getAnEntityManager();
	DataController dataController = new DataController();
	
      @GET
      @Produces(MediaType.TEXT_PLAIN)
      @Path("/getAllUsernames")
      public String getAllUsernames() {
    	  
    	try {
    	ArrayList<String> userNames = dataController.getAllUsernames(em);
    	em.close();
        return userNames.toString();
    	}
    	catch (Exception e) {
    		return "Error message of some kind";
    	}
      }
      
     
      
    //  @GET
    //  @Produces(MediaType.TEXT_PLAIN)
    //  @Path("/getAllUsernames")
   //   public String createNewUser() {
   // 	  
   // 	//ArrayList<String> userNames = dataController.getAllUsernames(em);
   // 	em.close();
   //    // return userNames.toString();
  //   return "Test";
   //   }


}
