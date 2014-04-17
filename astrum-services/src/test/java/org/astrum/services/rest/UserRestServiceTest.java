package org.astrum.services.rest;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.multipart.FormDataMultiPart;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext-services.xml"})	
public class UserRestServiceTest {
	

	@Test
	public void testReportGeneration() throws IOException, InterruptedException{
		Runtime rt = Runtime.getRuntime();
		
		//read file from temp location (this would typically be passed in)
		URL url = this.getClass().getResource("/report.pdf");
		File file = new File(url.getFile());
		FileInputStream fis = new FileInputStream(file);
		byte[] data = null;
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int next;
		
		next = fis.read();
		while (next > -1) {
		    bos.write(next);
		    next = fis.read();
		}
		bos.flush();
		data = bos.toByteArray();
		fis.close();
		
		
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
		
		fis = new FileInputStream(generatedXML);
		data = null;
		
		bos = new ByteArrayOutputStream();
		next = fis.read();
		while (next > -1) {
		    bos.write(next);
		    next = fis.read();
		}
		bos.flush();
		data = bos.toByteArray();
		fis.close();
		
		System.out.println("size of byte array is: "+data.length);
	}
	
	@Test
	public void testReportGenerationAlt() throws IOException, InterruptedException{
		Runtime rt = Runtime.getRuntime();
		String[] commandLine = new String[4];
		commandLine[0] = "/home/sancus/Downloads/pdfx_trial/pdfx";
		commandLine[1] = " --cue-dir ";
		commandLine[2] = " /home/sancus/Downloads/pdfx_trial/cues/english";
		commandLine[3] = " /home/sancus/Downloads/pdfx_trial/temp/report.pdf";
		Process pr = rt.exec(commandLine);
		
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
		
	    //System.out.println( output.toString());
	}
	@Test
	public void testEcho(){
		
		String random = UUID.randomUUID().toString();
		Client client = Client.create();
		WebResource resource = client.resource("http://sancus-vm:1776/astrum/rest/user/echo/"+random);
		
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		if(response.getStatus() != 200){
			throw new RuntimeException("Failed: HTTP error code: "+ response.getStatus());
		}
		System.out.println(response.getEntity(String.class));
	}
	@Test
	public void postFileToServer() throws IOException{
		InputStream stream = getClass().getClassLoader().getResourceAsStream("report.pdf");
		long startTime = System.currentTimeMillis();
		/////////
		
		/*byte[] data = null;
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int next = fis.read();
		while (next > -1) {
		    bos.write(next);
		    next = fis.read();
		}
		bos.flush();
		data = bos.toByteArray();
		fis.close();*/
		////////////
	    FormDataMultiPart part = new FormDataMultiPart().field("file", stream, MediaType.APPLICATION_OCTET_STREAM_TYPE);

	    WebResource resource = Client.create().resource("http://sancus-vm:1776/astrum/rest/user/report");
	    String response = resource.type(MediaType.MULTIPART_FORM_DATA_TYPE).post(String.class, part);
	    System.out.println(response);
	    long stopTime = System.currentTimeMillis();
	    long totalTime = (stopTime - startTime) /1000;
	    System.out.println("total time: "+totalTime);
	   // assertEquals("Hello, World", response);
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
