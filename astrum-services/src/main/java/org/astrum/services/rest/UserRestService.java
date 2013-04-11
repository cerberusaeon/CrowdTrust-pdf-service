package org.astrum.services.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.astrum.common.repository.PersonRepository;

@Path("/user")
public class UserRestService {

	
	@Inject
	PersonRepository personRepository;
	
	@GET
	@Path("/{userId}")
	@Produces("application/json")
	@Consumes("application/json")
	public Response getMsg(@PathParam("userId") String msg) {
 
		String output = "Astrum says : " + msg;
 
		return Response.status(200).entity(output).build();
	}
}
