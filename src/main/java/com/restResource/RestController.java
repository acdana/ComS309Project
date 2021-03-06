package com.restResource;

import java.awt.geom.Point2D;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import com.persistenceResource.DataController;
import com.persistenceResource.FactoryStartup;

/**
 * This is our RestController class that contains the necessary information to make REST calls.
 * It can be seen that all of our calls start with the 'T11' base path. When a return is in order,
 * the return is made as a JSON String.
 * 
 * Username and password should be sent via jQuery header with the AJAX call so that the user identity may be verified.
 */
@Path("/T11")
public class RestController {
	EntityManager em = FactoryStartup.getAnEntityManager();
	DataController dataController = new DataController();
    
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllUsernames")
	public String getAllUsernames(@Context HttpServletRequest req) {
		
		if(dataController.verifyCredentials(em, req) == true) {
		
			try {
				String userNames = dataController.getUsernames(em, "getAllUsernames");
				em.close();
				return userNames;
			} catch (Exception e) {
				em.close();
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
			}
			
		}
		else {
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
		}
	
	}

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/createNewUser/{username}/{password}/{email}")
	public String createNewUser(@PathParam("username") String username, @PathParam("password") String password, @PathParam("email") String email) {
			try {
				dataController.createNewUser(em, username, password, email);
				return "{\"Result\":[{\"Status\":\"Success\"}]}";
			} catch (Exception e) {
				em.close();
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
			}
			
		
	
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/deleteUser/{usernameToDelete}")
	public String deleteUser(@Context HttpServletRequest req, @PathParam("usernameToDelete") String usernameToDelete) {		
		
		if(dataController.verifyCredentials(em, req) == true && dataController.getUserType(em, req).equals("{\"Result\":\"Admin\"}")) {	
			try {
				em.getTransaction().begin();
				String statusMessage = dataController.deleteUser(em, usernameToDelete);
				em.getTransaction().commit();
				em.close();
				return "{\"Result\":[{\"Status\":\"" + statusMessage + "\"}]}";
			} catch (Exception e) {
				em.close();
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
			}
			
		}	
		else {
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";			
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/penalizeUser/{usernameToPenalize}")
	public String penalizeUser(@Context HttpServletRequest req, @PathParam("usernameToPenalize") String usernameToPenalize) {

		if(dataController.verifyCredentials(em, req) == true && (dataController.getUserType(em, req).equals("{\"Result\":\"Admin\"}") || dataController.getUserType(em, req).equals("{\"Result\":\"Moderator\"}"))) {	
		
			try {
				em.getTransaction().begin();
				String statusMessage = dataController.penalizeUser(em, usernameToPenalize);
				em.getTransaction().commit();
				em.close();
				return "{\"Result\":[{\"Status\":\"" + statusMessage + "\"}]}";

			} catch (Exception e) {
				em.close();
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
			}
		
		}
		else {
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
		}

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getPenalizedUsers")
	public String getPenalizedUsers(@Context HttpServletRequest req) {

		if(dataController.verifyCredentials(em, req) == true) {	
			
			try {
				String userNames = dataController.getUsernames(em, "getPenalizedUsers");
				em.close();
				return userNames;
			} catch (Exception e) {
				em.close();
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
			}
			
		}
		else {
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
		}
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getPenaltyCount/{usernameToCheck}")
	public String getPenaltyCount(@Context HttpServletRequest req, @PathParam("usernameToCheck") String usernameToCheck) {

		if(dataController.verifyCredentials(em, req) == true) {	
			
			try {
				String penaltyCount = dataController.getPenaltyCount(em, usernameToCheck);
				em.close();
				return "{\"Result\":[{\"Status\":\"Success\"}, {\"PenaltyCount\":\"" + penaltyCount + "\"}]}";
			} catch (Exception e) {
				em.close();
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
			}
		
		}
		else {
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getMessages/{username}")
	public String getMessages(@Context HttpServletRequest req, @PathParam("username") String username) {
		
		if(dataController.verifyCredentials(em, req) == true) {
		
			try {
				String messages = dataController.getMessages(em, username);
				em.close();
				return messages;
			} catch (Exception e) {
				em.close();
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
			}
		
		}
		else {
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/createNewMessage/{username}/{message}/{sender}")
	public String createNewMessage(@Context HttpServletRequest req, @PathParam("username") String username, @PathParam("message") String message, @PathParam("sender") String sender) {

		if(dataController.verifyCredentials(em, req) == true) {
		
			try {
				dataController.createNewMessage(em, username, message, sender);
				return "{\"Result\":[{\"Status\":\"Success\"}]}";
			} catch (Exception e) {
				em.close();
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
			}
		
		}
		else {
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
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
			return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
		}
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getUserType/{username}")
	public String getUserType(@Context HttpServletRequest req, @PathParam("username") String username) {

		if(dataController.verifyCredentials(em, req) == true) {	
		
			try {
				String output = dataController.getUserType(em, username);
				em.close();
				return "{\"Result\":[{\"Status\":\"Success\"}, {\"UserType\":\"" + output + "\"}]}";
			} catch (Exception e) {
				em.close();
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
			}

		}
		else {
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/changeUserType/{username}/{userType}")
	public String promoteUser(@Context HttpServletRequest req, @PathParam("username") String username, @PathParam("userType") String userType) {

		if(dataController.verifyCredentials(em, req) == true) {	
			
			try {
				em.getTransaction().begin();
				
				String result = dataController.changeUserType(em, username, userType);	

				em.getTransaction().commit();
				em.close();
				return "{\"Result\":[{\"Status\":\"" + result + "\"}]}";

			} catch (Exception e) {
				em.close();
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
			}
			
		}
		else {
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
		}

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getUsers/{userType}")
	public String getUsers(@Context HttpServletRequest req, @PathParam("userType") String userType) {
		
		if(dataController.verifyCredentials(em, req) == true) {
		
			try {
				String userNames = dataController.getUsers(em, userType);
				em.close();
				return userNames;
			
			} catch (Exception e) {
				em.close();
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
			}
		
		}
		else {
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAverageSaleLocation")
	public String getAverageLocation(@Context HttpServletRequest req) {
		
		if(dataController.verifyCredentials(em, req) == true) {
		
			try {
				Point2D.Double averageLocation = dataController.getAverageSaleLocation(em);
				em.close();
				return "{\"Result\":[{\"Status\":\"Success\"}, {\"Location\":[{\"longitude\":\"" + averageLocation.getX() + "\", \"latitude\":\"" + averageLocation.getY() + "\"}]}]}";

			} catch (Exception e) {
				em.close();
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
			}
		
		}
		else {
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getStatus/{username}")
	public String getStatus(@Context HttpServletRequest req, @PathParam("username") String username) {
		
		if (dataController.verifyCredentials(em, req) == true) {
			
			try {
				
				String output = dataController.getStatus(em, username);
				if (output.equalsIgnoreCase("none")) {
					
					return "{\"Result\":[{\"Status\":\"Failure\"}]}";
					
				}
				
				return "{\"Result\":[{\"Status\":\"Success\"}, {\"Account Status\":\"" + output + "\"}]}";
				
			} catch (Exception e) {
				
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
				
			}
			
		} else {
			
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
		
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getProfPic/{username}")
	public String getProfPic(@Context HttpServletRequest req, @PathParam("username") String username) {
		
		if (dataController.verifyCredentials(em, req) == true) {
			
			try {
				
				String output = dataController.getProfPic(em, username);
				if (output.equalsIgnoreCase("none")) {
					
					return "{\"Result\":[{\"Status\":\"Failure\"}]}";
					
				}
				return "{\"Result\":[{\"Status\":\"Success\"}, {\"ProfilePic\":\"" + output + "\"}]}";
				
			} catch (Exception e) {
				
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
				
			}
			
		} else {
			
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
		
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getBio/{username}")
	public String getBio(@Context HttpServletRequest req, @PathParam("username") String username) {
		
		if (dataController.verifyCredentials(em, req) == true) {
			
			try {
				
				String output = dataController.getBio(em, username);
				if (output.equalsIgnoreCase("none")) {
					
					return "{\"Result\":[{\"Status\":\"Failure\"}]}";
					
				}
				
				return "{\"Result\":[{\"Status\":\"Success\"}, {\"Bio\":\"" + output + "\"}]}";
				
			} catch (Exception e) {
				
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
				
			}
			
		} else {
			
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
		
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getTotTrades/{username}")
	public String getTotTrades(@Context HttpServletRequest req, @PathParam("username") String username) {
		
		if (dataController.verifyCredentials(em, req) == true) {
			
			try {
				
				String output = dataController.getTotTrades(em, username);
				if (output.equalsIgnoreCase("none")) {
					
					return "{\"Result\":[{\"Status\":\"Failure\"}]}";
					
				}
				
				return "{\"Result\":[{\"Status\":\"Success\"}, {\"Trades\":\"" + output + "\"}]}";
				
			} catch (Exception e) {
				
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
				
			}
			
		} else {
			
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
		
		}
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getOpenSales/{username}")
	public String getOpenSales(@Context HttpServletRequest req, @PathParam("username") String username) {
		
		if (dataController.verifyCredentials(em, req) == true) {
			
			try {
				
				long output = dataController.getOpenSales(em, username);
				
				return "{\"Result\":[{\"Status\":\"Success\"}, {\"Sales\":\"" + output + "\"}]}";
				
			} catch (Exception e) {
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
				
			}
			
		} else {
			
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
		
		}
		
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getMessageData")
	public String getData() {
		
			try {
				
				double length = dataController.getMessageData(em);
				em.close();
				return "{\"Result\":[{\"Status\":\"Success\"}, {\"AverageLength\":\"" + length + "\"}]}";
				
			} catch (Exception e) {
				
				em.close();
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
				
			}
		
	}
	
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getCurrentSales")
	public String getCurrentSales() {
		
			try {
				
				String output = dataController.getCurrentSales(em);
				return output;
				
			} catch (Exception e) {
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
				
			}

		
	}
	
	
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/createSale/{description}/{tags}")
	public String createSale(@Context HttpServletRequest req, @PathParam("description") String description, @PathParam("tags") String tags) {
		
		if (dataController.verifyCredentials(em, req) == true) {
		
			try {
				
				String output = dataController.createSale(em, req, description, tags);
				return "{\"Result\":[{\"Status\":\"" + output + "\"}]}";
				
			} catch (Exception e) {
				
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
				
			}
		} else {
			
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
		
		}
		
	}
	
	
	@POST
	@Path("/updateProfile/{username}/{bio}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateProfile(@Context HttpServletRequest req, @PathParam("username") String username, @PathParam("bio") String bio) {
		
		if (dataController.verifyCredentials(em, req) == true) {
			
			try {
				
				boolean success = dataController.updateProfile(em, username, bio);
				if (success) {
					
					return "{\"Result\":[{\"Status\":\"Success\"}]}";
					
				} else {
					
					return "{\"Result\":[{\"Status\":\"Failure\"}]}";
					
				}
				
			} catch (Exception e) {
				
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
				
			}
			
		} else {
			
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
			
		}
		
	}
	
	@POST
	@Path("/updateProfilePic/{username}/{pic}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateProfilePic(@Context HttpServletRequest req, @PathParam("username") String username, @PathParam("pic") String profPic) {
		
		if (dataController.verifyCredentials(em, req) == true) {
			
			try {
				profPic = profPic.replaceAll("~", "/");
				boolean success = dataController.updateProfilePic(em, username, profPic);
				if (success) {
					
					return "{\"Result\":[{\"Status\":\"Success\"}]}";
					
				} else {
					
					return "{\"Result\":[{\"Status\":\"Failure\"}]}";
					
				}
				
			} catch (Exception e) {
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
				
			}
			
		} else {
			
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
			
		}

		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/createItem/{saleID}/{sellerName}/{itemName}")
	public String createItem(@Context HttpServletRequest req, @PathParam("saleID") String saleID, @PathParam("sellerName") String sellerName, @PathParam("itemName") String itemName) {
		
		if (dataController.verifyCredentials(em, req) == true) {
			
			try {
				
				String output = dataController.createItem(em, sellerName, itemName, saleID);
				return "{\"Result\":[{\"Status\":\"" + output + "\"}]}";
				
			} catch (Exception e) {
				
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
				
			}
			
		} else {
			
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
			
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getUsersItems/{username}")
	public String getUsersItems(@Context HttpServletRequest req, @PathParam("username") String username) {
		
		if (dataController.verifyCredentials(em, req) == true) {
			
			try {
				
				String result = dataController.getUsersItems(em, username);
				String output = "{\"Result\":[{\"Status\":\"Success\"}, " + result + "]}";
				return output;
				
			} catch (Exception e) {
				
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
				
			}
			
		} else {
			
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
			
		}
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/tradeItem/{itemID}/{saleID}")
	public String tradeItem(@Context HttpServletRequest req, @PathParam("itemID") String itemID, @PathParam("saleID") String saleID) {
		
		if (dataController.verifyCredentials(em, req)) {
			
			try {
				
				String result = dataController.tradeItem(em, itemID, saleID);
				return "{\"Result\":[{\"Status\":\"" + result + "\"}]}";

			} catch (Exception e) {
				
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
				
			}
			
		} else {

			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
			
		}
		
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getReputation/{username}")
	public String getReputation(@Context HttpServletRequest req, @PathParam("username") String username) {
		
		if (dataController.verifyCredentials(em, req)) {
			
			try {
				
				String result = dataController.getReputation(em, username);
				return "{\"Result\":[{\"Status\":\"Success\"}, " + "{\"Reputation\":\"" + result + "\"}]}";

			} catch (Exception e) {
				
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
				
			}
			
		} else {

			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
			
		}
		
	}
	
	
	
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getPrimarySeller/{saleID}")
	public String getPrimarySeller(@Context HttpServletRequest req, @PathParam("saleID") String saleID) {
		
		if (dataController.verifyCredentials(em, req)) {
			
			try {
				
				String result = dataController.getPrimarySeller(em, saleID);
				return "{\"Result\":[{\"Status\":\"Success\"}, " + "{\"Seller\":\"" + result + "\"}]}";

			} catch (Exception e) {
				
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
				
			}
			
		} else {

			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
			
		}
		
	}

	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/setSaleData/{saleID}/{lat}/{lon}/{primarySellerLocation}/{secondarySellerLocation}/{secondarySeller}")
	public String setSaleData(@Context HttpServletRequest req, @PathParam("saleID") String saleID, @PathParam("lat") String lat, @PathParam("lon") String lon, @PathParam("primarySellerLocation") String primarySellerLocation, @PathParam("secondarySellerLocation") String secondarySellerLocation, @PathParam("secondarySeller") String secondarySeller) {
		
		if (dataController.verifyCredentials(em, req) == true) {
			
			try {
				
				boolean output = dataController.setSaleData(em, saleID, lat, lon, primarySellerLocation, secondarySellerLocation, secondarySeller);
				return "{\"Result\":[{\"Status\":\"" + output + "\"}]}";
				
			} catch (Exception e) {
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
				
			}
			
		} else {
			
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
			
		}
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/setSaleData/{saleID}/{secondarySeller}")
	public String setSaleData(@Context HttpServletRequest req, @PathParam("saleID") String saleID, @PathParam("secondarySeller") String secondarySeller) {
		
		if (dataController.verifyCredentials(em, req) == true) {
			
			try {
				
				boolean output = dataController.setSaleSecondarySeller(em, saleID, secondarySeller);
				return "{\"Result\":[{\"Status\":\"" + output + "\"}]}";
				
			} catch (Exception e) {
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
				
			}
			
		} else {
			
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
			
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getSaleTags/{saleID}")
	public String getSaleTags(@Context HttpServletRequest req, @PathParam("saleID") String saleID) {
		
		if (dataController.verifyCredentials(em, req)) {
			
			try {
				
				String result = dataController.getSaleTags(em, saleID);
				return "{\"Result\":[{\"Status\":\"Success\"}, " + "{\"Tags\":\"" + ((result == null) ? "" : result) + "\"}]}";

			} catch (Exception e) {
				
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
				
			}
			
		} else {

			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
			
		}
		
	}
	
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllSaleLocations")
	public String getAllSaleLocations(@Context HttpServletRequest req) {
		
		if(dataController.verifyCredentials(em, req) == true) {
		
			try {
				String allLocations = dataController.getAllSaleLocations(em);
				em.close();
				return allLocations;

			} catch (Exception e) {
				em.close();
				return "{\"Result\":[{\"Status\":\"Exception Failure\"}]}";
			}
		
		}
		else {
			return "{\"Result\":[{\"Status\":\"Credential Failure\"}]}";
		}
	}
}
