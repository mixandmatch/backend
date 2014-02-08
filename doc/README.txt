TESTEN MIT SOAPUI

wadl import:
http://localhost:8080/mam-backend-0.0.1.BUILD-SNAPSHOT-dev/rest/application.wadl

Testdaten Beispiele:

getOrCreateUser:
{"username":"Max"}

appointments_add:
{"appointmentDate":1459771678807,"appointmentLocation":1,"ownerID":1}

addParticipant:
{"username":"Max"}


removeParticipant:
{"username":"Max"}