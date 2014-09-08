package com.resource;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.db.DB;

 
@Path("/BasicUserResource")
public class UserResource {
 
@GET
@Produces(MediaType.TEXT_PLAIN)
public String getAllBasicUsers() {
	return DB.getAllBasicUsers();
}
 
@GET
@Produces(MediaType.TEXT_PLAIN)
@Path("{name}")
public String getBasicUser(@PathParam("name") String name) {
return DB.getBasicUser(name);
}
 
}