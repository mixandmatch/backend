var _restify = require('restify');
var _ = require('underscore');
var _sessionService = require('./node_lib/sessionservice.js');
var _dateService = require('./node_lib/dateservice.js');
var _server = _restify.createServer();

_server.use(_restify.bodyParser());
_server.use(function noCache(req, res, next){
    res.header("Cache-Control", "no-cache, no-store, must-revalidate");
    res.header("Pragma", "no-cache");
    res.header("Expires",0);
    next();
});

//session
_server.post('/service/loginUser', _sessionService.loginUser);
_server.get('/service/getUserDetails/:guid/:userName', _sessionService.getUserDetails);
_server.get('/service/logoutUser/:guid', _sessionService.logoutUser);

//dateItems
_server.get('/service/fetchDateItemsByRange/:guid/:dateTimeStart/:dateTimeEnd', _dateService.fetchDateItemsByRange);
_server.get('/service/fetchDateItemsByOwnerName/:guid/:ownerName', _dateService.fetchDateItemsByOwnerName);
_server.post('/service/addDateItem/', _dateService.addDateItem);
_server.get('/service/deleteDateItem/:guid/:dateGuid', _dateService.deleteDateItem);
_server.get('/service/addAttendee/:guid/:dateGuid/:userName', _dateService.addAttendee);
_server.get('/service/removeAttendee/:guid/:dateGuid/:userName', _dateService.removeAttendee);

//static
_server.get(/\/?.*/, _restify.serveStatic({
  directory: __dirname + "/app",
  default: 'index.html'
}));

_server.listen(8080, function() {
  console.log('listening at %s', _server.url);
});