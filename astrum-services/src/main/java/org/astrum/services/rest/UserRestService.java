package org.astrum.services.rest;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/user")
public class UserRestService {

	public static String REPORT_PATH = "/home/sancus/Downloads/pdfx_trial/temp/";
	
	public static Logger logger = LoggerFactory.getLogger(UserRestService.class);

	@GET
	@Path("/echo/{userId}")
	@Produces("application/json")
	@Consumes("application/json")
	public Response getMsg(@PathParam("userId") String msg) {
 
		String output = "Astrum says : " + msg;
 
		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("/report")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response convertReport(@FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException, InterruptedException{
	Runtime rt = Runtime.getRuntime();
		
		//read file from temp location (this would typically be passed in)
		/*URL url = this.getClass().getResource("/report.pdf");
		File file = new File(url.getFile());
		FileInputStream fis = new FileInputStream(file);*/
		byte[] data = null;
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int next;
		
		next = uploadedInputStream.read();
		while (next > -1) {
		    bos.write(next);
		    next = uploadedInputStream.read();
		}
		bos.flush();
		data = bos.toByteArray();
		//fis.close();
		
		
		String random = UUID.randomUUID().toString();
		//write file to uuid directory
       
	    //convert array of bytes into file
		 File myFile = new File("/home/sancus/Downloads/pdfx_trial/temp/"+random+"/report.pdf");
		 File myDirectory = new File("/home/sancus/Downloads/pdfx_trial/temp/"+random+"/");
		    if(!myDirectory.exists()) {
		    	boolean createdDirectory = myDirectory.mkdirs();
		        boolean createdFile = myFile.createNewFile();
		        System.out.println("dir: "+createdDirectory+" file: "+createdFile);
		    } 
	    FileOutputStream fileOuputStream = new FileOutputStream("/home/sancus/Downloads/pdfx_trial/temp/"+random+"/report.pdf");
	   
	    
	    fileOuputStream.write(data);
	    fileOuputStream.close();
		
		Process pr = rt.exec("/home/sancus/Downloads/pdfx_trial/pdfxRun.sh "+random);
		
		int value = pr.waitFor();
		
		InputStream is = pr.getErrorStream();
		String result = getStringFromInputStream(is);
		
		InputStream is2 = pr.getInputStream();
		String result2 = getStringFromInputStream(is2);
		 
		System.out.println("value: "+value);
		System.out.println(result);
		System.out.println("Done");
		
		System.out.println(result2);
		System.out.println("Done");
		
		System.out.println("RANDOM: "+random);
		
	    //retrieve generated file and send as byte array
		File generatedXML = new File("/home/sancus/Downloads/pdfx_trial/temp/"+random+"/report.pdfx.xml");
		System.out.println("does generated xml file exist: "+generatedXML.exists());
		
		FileInputStream fis = new FileInputStream(generatedXML);
		data = null;
		
		bos = new ByteArrayOutputStream();
		next = fis.read();
		while (next > -1) {
		    bos.write(next);
		    next = fis.read();
		}
		bos.flush();
		data = bos.toByteArray();
		//fis.close();
		
		System.out.println("size of byte array is: "+data.length);
		return Response.ok(data).build();
		//return Response.ok(IOUtils.toString(fis)).build();
	}
	
	@GET
	@Path("/netstat")
	@Produces("application/json")
	@Consumes("application/json")
	public Response getNetStatOutput() throws IOException, InterruptedException{
		String currDocument = REPORT_PATH+"report.pdf";
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("/home/sancus/Downloads/pdfx_trial/pdfxRun.sh");
		
		int value = pr.waitFor();
		
		InputStream is = pr.getErrorStream();
		String result = getStringFromInputStream(is);
		
		InputStream is2 = pr.getInputStream();
		String result2 = getStringFromInputStream(is2);
		 
		System.out.println("value: "+value);
		System.out.println(result);
		System.out.println("Done");
		
		System.out.println(result2);
		System.out.println("Done");
		return Response.status(200).entity(currDocument+" time:"+value ).build();
	}
	private static String getStringFromInputStream(InputStream is) {
		 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
		return sb.toString();
 
	}

}
