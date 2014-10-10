package com.restResource;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.persistenceResource.DataController;
import com.persistenceResource.FactoryStartup;

/**
 * This is our RestController class that contains the necassary information to make REST calls.
 * It can be seen that all of our calls start with the 'T11' base path. When a return is in order,
 * the return is made as a JSON String.
 */
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
		} catch (Exception e) {
			em.close();
			return "Error message of some kind";
		}
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/createNewUser/{username}/{password}/{email}")
	public String createNewUser(@PathParam("username") String username, @PathParam("password") String password, @PathParam("email") String email) {

		try {
			dataController.createNewUser(em, username, password, email);
			return "Success";
		} catch (Exception e) {
			em.close();
			return e.toString();
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
		} catch (Exception e) {
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
			if (userNames == null)
				return "No penalized users.";
			else
				return userNames.toString();

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
		} catch (Exception e) {
			em.close();
			return "Failure";
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userLogin/{username}/{password}")
	public String userLogin(@PathParam("username") String username, @PathParam("password") String password) {

		try {
			String loginStatus = dataController.userLogin(em, username, password);
			return loginStatus;
		} catch (Exception e) {
			em.close();
			return "Failure - Database Error";
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getUserType/{username}")
	public String getUserType(@PathParam("username") String username) {

		try {

			String output = dataController.getUserType(em, username);
			em.close();
			return output;

		} catch (Exception e) {

			em.close();
			return e.getMessage();

		}

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/promoteUser/{username}")
	public String promoteUser(@PathParam("username") String username) {

		try {

			em.getTransaction().begin();
			String type = dataController.getUserType(em, username);
			String result;
			if (type.equalsIgnoreCase("Basic")) {

				result = dataController.changeUserType(em, username, "Moderator");

			} else if (type.equalsIgnoreCase("Moderator")) {

				result = dataController.changeUserType(em, username, "Admin");

			} else if (type.equalsIgnoreCase("Admin")) {

				result = "Cannot promote an admin.";

			} else {

				result = type;

			}

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
	@Path("/demoteUser/{username}")
	public String demoteUser(@PathParam("username") String username) {

		try {

			em.getTransaction().begin();
			String type = dataController.getUserType(em, username);
			String result;
			if (type.equalsIgnoreCase("Basic")) {

				result = "Cannot demote a basic user.";

			} else if (type.equalsIgnoreCase("Moderator")) {

				result = dataController.changeUserType(em, username, "Basic");

			} else if (type.equalsIgnoreCase("Admin")) {

				result = dataController.changeUserType(em, username, "Moderator");

			} else {

				result = type;

			}

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
	@Path("/getBasics")
	public String getBasics() {

		try {

			ArrayList<String> userNames = dataController.getUsernames(em, "getBasics");
			em.close();
			if (userNames == null)
				return "No basic users.";
			else
				return userNames.toString();

		} catch (Exception e) {
			em.close();
			return e.getMessage();
		}

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getModerators")
	public String getModerators() {

		try {

			ArrayList<String> userNames = dataController.getUsernames(em, "getModerators");
			em.close();
			if (userNames == null)
				return "No moderators.";
			else
				return userNames.toString();

		} catch (Exception e) {
			em.close();
			return e.getMessage();
		}

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAdmins")
	public String getAdmins() {
		
		try {

			ArrayList<String> userNames = dataController.getUsernames(em, "getAdmins");
			em.close();
			if (userNames == null)
				return "No admins.";
			else
				return userNames.toString();

		} catch (Exception e) {
			em.close();
			return e.getMessage();
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAverageSaleLocation")
	public String getAverageLocation() {
		
		try {

			Point2D.Double averageLocation = dataController.getAverageSaleLocation(em);
			em.close();

			return "{\"Location\":[{\"longitude\":\"" + averageLocation.getX() + "\", \"latitude\":\"" + averageLocation.getY() + "\"}]}";

		} catch (Exception e) {
			em.close();
			return e.getMessage();
		}
		
	}

}
