package com.restResource;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.entities.User;
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
     
      
      @GET
      @Produces(MediaType.TEXT_PLAIN)
      @Path("/createNewUser/{username}/{password}/{email}")
      public String createNewUser(@PathParam("username") String username, @PathParam("password") String password, @PathParam("email") String email) {
    	 
    	  try {
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
    		  return "Success";
    	  }
    	  catch(Exception e) {
    		  return "Failure";
    	  }
      }


      @GET
      @Produces(MediaType.TEXT_PLAIN)
      @Path("/deleteUser/{usernameToDelete}")
      public String deleteUser(@PathParam("usernameToDelete") String usernameToDelete) {
    	  
    	try {
    		em.getTransaction().begin();
    		String result = dataController.deleteUser(em, usernameToDelete);
    		em.getTransaction().commit();
    		em.close();
    		return result;
    	}
    	catch (Exception e) {
    		return e.getMessage();
    	}
      }
      
}
