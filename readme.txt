The NodeJS server runs two modules, static and restify
- static is configured to serve static files (eg. AngularJS) from the folder "app" under the server address "/*"
- restify is configured to handle REST calls under the server address "/service/*"

NOTE: since the restify module takes higher priority over the static module, no folders called service can be served from the app folder