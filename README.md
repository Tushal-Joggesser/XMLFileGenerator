# XMLFileGenerator
Generate XML file as per Fatca and CRS templates

The sample code create xml files as per Fatca and crs templates for a specific client. 

# Creating of the XML files
1. Each class has it's own hierarchy for object build up as per root xml element. 
2. XML was created using JAXBContext
3. Json was created using JsonObject or JAXBContext (This is optional)

# HTTP POST
1. A sample HTTP POST method was created to capture the details via HTTP servlet (Path " FAMU-1/MuReq ")
2. The application war file can be deployed on wildfly server or any JBOSS server.
3. Main purpose for the servlet is to capture the request and build the XML file if ever the client needs to upload build records via API
