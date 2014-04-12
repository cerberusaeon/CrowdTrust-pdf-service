package org.astrum.services.rest;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserRestService {


	@GET
	@Path("/echo/{userId}")
	@Produces("application/json")
	@Consumes("application/json")
	public Response getMsg(@PathParam("userId") String msg) {
 
		String output = "Astrum says : " + msg;
 
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("/netstat")
	@Produces("application/json")
	@Consumes("application/json")
	public Response getNetStatOutput() throws IOException, InterruptedException{
		String output = "whatever man.";
		Runtime rt = Runtime.getRuntime();
		//Process pr = rt.exec("netstat -ano > D:\\myGeneratedFile["+new Date()+".txt \n");
		Process pr = rt.exec("cmd /c netstat -ano > D:\\myGeneratedFile.txt \n");
		int value = pr.waitFor();
		return Response.status(200).entity(output+" time:"+value ).build();
	}
}
