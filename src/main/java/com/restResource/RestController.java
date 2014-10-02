package com.restResource;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.persistenceResource.DataController;
import com.persistenceResource.FactoryStartup;

@Path("/T11")
public class RestController {
	EntityManager em = FactoryStartup.getAnEntityManager();
	DataController dataController = new DataController();
	
      @GET
      @Produces(MediaType.APPLICATION_JSON)
      @Path("/getAllUsernames")
      public String getAllUsernames() {
    	  
    	try {
    		ArrayList<String> userNames = dataController.getUsernames(em, "getAllUsernames");
    		em.close();
    		return userNames.toString();
    	}
    	catch (Exception e) {
    		em.close();
    		return "Error message of some kind";
    	}
      }
     
      
      @GET
      @Produces(MediaType.APPLICATION_JSON)
      @Path("/createNewUser/{username}/{password}/{email}")
      public String createNewUser(@PathParam("username") String username, @PathParam("password") String password, @PathParam("email") String email) {
    	 
    	  try {
    		  dataController.createNewUser(em, username, password, email);
    		  return "Success";
    	  }
    	  catch(Exception e) {
    		  em.close();
    		  return "Failure";
    	  }
      }


      @GET
      @Produces(MediaType.APPLICATION_JSON)
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
    		em.close();
    		return e.getMessage();
    	}
      }
      
      @GET
      @Produces(MediaType.APPLICATION_JSON)
      @Path("/penalizeUser/{usernameToPenalize}")
      public String penalizeUser(@PathParam("usernameToPenalize") String usernameToPenalize) {
    	  
    	  try {
    		  
    		  em.getTransaction().begin();
    		  String result = dataController.penalizeUser(em, usernameToPenalize);
    		  em.getTransaction().commit();
    		  em.close();
    		  return result;
    		  
    	  } catch (Exception e) {
    		  em.close();
    		  return e.getMessage();
    	  }
    	  
      }
      
      @GET
      @Produces(MediaType.APPLICATION_JSON)
      @Path("/getPenalizedUsers")
      public String getPenalizedUsers() {
    	  
    	try {
    		
    		ArrayList<String> userNames = dataController.getUsernames(em, "getPenalizedUsers");
    		em.close();
    		if (userNames == null) return "No penalized users.";
    		else return userNames.toString();
    	
    	} catch (Exception e) {
    		em.close();
    		return e.getMessage();
    	}
    	
      }
      
      @GET
      @Produces(MediaType.APPLICATION_JSON)
      @Path("/getPenaltyCount/{usernameToCheck}")
      public String getPenaltyCount(@PathParam("usernameToCheck") String usernameToCheck) {
    	
    	  try {
    		  
    		  String output = dataController.getPenaltyCount(em, usernameToCheck);
    		  em.close();
    		  return output;
    		  
    	  } catch (Exception e) {
    		  em.close();
    		  return e.getMessage();
    	  }
    	  
      }
      
      @GET
      @Produces(MediaType.APPLICATION_JSON)
      @Path("/getMessages/{username}")
      public String getMessages(@PathParam("username") String username) {
    	  try {
    		  
    		  String messages = dataController.getMessages(em, username);
    		  em.close();
    		  return messages;
    		  
    	  } catch (Exception e) {
    		  em.close();
    		  return e.getMessage();
    	  }
    	  
      }
      
      
      @GET
      @Produces(MediaType.APPLICATION_JSON)
      @Path("/createNewMessage/{username}/{message}/{sender}")
      public String createNewMessage(@PathParam("username") String username, @PathParam("message") String message, @PathParam("sender") String sender) {
 
    	  try {
    		  dataController.createNewMessage(em, username, message, sender);
    		  return "Success";
    	  }
    	  catch(Exception e) {
    		  em.close();
    		  return "Failure";
    	  }
      }
      
      
}
