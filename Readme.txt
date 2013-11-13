This is a simple Test Backend created with Spring Roo


REST URLs:
==========

curl -i -H "Accept: application/json" http://localhost:8080/MixMatchRooRestTestService/appointments
curl -i -H "Accept: application/json" http://localhost:8080/MixMatchRooRestTestService/locations?find=ByLocationNameLike&locationName=test


Locations
---------
Add a location:
curl -i -v -H "Accept: application/json" -X POST -H "Content-Type: application/json" -d '{"locationName":"Somewhere"}' http://localhost:8080/MixMatchRooRestTestService/locations

Add multiple locations using a json arrray:
curl -i -v -H "Accept: application/json" -X POST -H "Content-Type: application/json" -d '[{"locationName":"Somewhere 1"}, {"locationName":"Somewhere 2"}]' http://localhost:8080/MixMatchRooRestTestService/locations/jsonArray

Get all locations:
curl -i -H "Accept: application/json" http://localhost:8080/MixMatchRooRestTestService/locations

Get specific location by ID:
curl -i -H "Accept: application/json" http://localhost:8080/MixMatchRooRestTestService/locations

Users
-----
Add a user:
curl -i -v -H "Accept: application/json" -X POST -H "Content-Type: application/json" -d '{"username":"testuser"}' http://localhost:8080/MixMatchRooRestTestService/useres

Get all users:
curl -i -H "Accept: application/json" http://localhost:8080/MixMatchRooRestTestService/useres

Find user by name:
curl -i -H "Accept: application/json" http://localhost:8080/MixMatchRooRestTestService/useres?find=ByUsernameEquals&username=user+1
Returns empty json array if user doesn't exist.

Get user with id:
curl -i -H "Accept: application/json" http://localhost:8080/MixMatchRooRestTestService/useres/2


Appointments
------------
Add an appointment:
curl -i -v -H "Accept: application/json" -X POST -H "Content-Type: application/json" -d '{"appointmentDate":1383951600000,"appointmentLocation":1,"ownerID":1}' http://localhost:8080/MixMatchRooRestTestService/appointments
Where appointmentLocation and ownerID are IDs of existing locations/users

Get all appointments:
curl -i -H "Accept: application/json" http://localhost:8080/MixMatchRooRestTestService/appointments

Get appointment with ID:
curl -i -H "Accept: application/json" http://localhost:8080/MixMatchRooRestTestService/appointments/1

Add a participant to an appointment:
curl -i -v -H "Accept: application/json" -X POST -H "Content-Type: application/json" -d '{"username":"testuser"}' http://localhost:8080/MixMatchRooRestTestService/appointments/1/addParticipant

URL:
http://localhost:8080/MixMatchRooRestTestService/appointments/{appointmentID}/addParticipant



To get a JSON representation of the page displayed in the browser just add .json
Samples:
http://localhost:8080/MixMatchRooRestTestService/locations.json
http://localhost:8080/MixMatchRooRestTestService/locations.json?find=ByLocationNameLike&locationName=test


Standalone Jar
==============

To create a standalone jar with an embedded tomcat server add the following to the pom.xml and run mvn package.

			<plugin>
				<groupId>org.apache.tomcat.maven</groupId>
				<artifactId>tomcat7-maven-plugin</artifactId>
				<version>2.1</version>
				<executions>
					<execution>
						<id>tomcat-run</id>
						<goals>
							<goal>exec-war-only</goal>
						</goals>
						<phase>package</phase>
						<configuration>
							<path>/MixMatchRooRestTestService</path>
							<!-- optional, needed only if you want to use a preconfigured server.xml 
								file <serverXml>src/main/tomcatconf/server.xml</serverXml> -->
							<!-- optional values which can be configurable -->
							<enableNaming>false</enableNaming>
							<finalName>MixMatchRooRestTestService.jar</finalName>
							<charset>utf-8</charset>
							<attachArtifactClassifier>exec-war</attachArtifactClassifier>
							<attachArtifactClassifierType>jar</attachArtifactClassifierType>
						</configuration>
					</execution>
				</executions>
			</plugin>

To run the server use:
java -jar MixMatchRooRestTestService.jar
The URL stays the same.