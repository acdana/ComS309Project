package com.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class RestController {

      // This method is called if TEXT_PLAIN is request
      @GET
      @Produces(MediaType.TEXT_PLAIN)
      public String tempRestCall() {
        return "This needs replaced with a data call.";
      }


}
