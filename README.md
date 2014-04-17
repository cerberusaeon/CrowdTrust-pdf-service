-------------------------------------------------------------------------------
Astrum
-------------------------------------------------------------------------------
Version 0.0.1
Release date: N/A
-------------------------------------------------------------------------------
Project state: prototype 
-------------------------------------------------------------------------------
Credits
	Stephen Ohimor stephen.ohimor@gmail.com

Project description	
-------------------------------------------------------------------------------
Generic web application with javascript front end


Dependencies
-------------------------------------------------------------------------------

Hibernate
Hsqldb


Documentation
-------------------------------------------------------------------------------

Servlet context Simple Endpoint: http://localhost:8080/astrum/rest/user/{yourMessage}

Installation instructions
-------------------------------------------------------------------------------

Install JDK 1.6+
Install Maven 3

git clone https://github.com/cerberusaeon/astrum.git

build:
  mvn clean package


-------------------------------------------------------------------------------
Additional Notes

astrum-common - contains domain/model objects
astrum-services - contains REST services/endpoints for app
astrum-web - js/image


pdf-service was forked from a generic project (astrum) for the purposes of wrapping and NLP/PDF engine and exposing it via a webservice.  The project can only be hosted on a UNIX machine due to the limitations of the engine.  Processing time is a bit slow (approx 1 min) but service appears to function very well.

Service is hosted via domain mangrovesolutions.net or on.blendout.net on port 1777

To test if the service is up hit
http://mangrovesolutions.net:1777/astrum/rest/user/echo/${yourString}   


To use service post a Media type of MULTIPART_FORM_DATA_TYPE.  The services expects the type to be an octect input stream.  
http://mangrovesolutions.net:1777/astrum/rest/user/report
-------------------------------------------------------------------------------
