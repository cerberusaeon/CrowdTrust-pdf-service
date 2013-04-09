package org.astrum.services.rest.model;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserService {

	
	@GET
	@Path("/{userId}")
	public Response getMsg(@PathParam("userId") String msg) {
 
		String output = "Astrum says : " + msg;
 
		return Response.status(200).entity(output).build();
	}
}
