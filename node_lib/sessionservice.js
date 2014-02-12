'use strict';

/* Session Service */

var _ = require('underscore');
var _data = require('./data.js');

function getUserDetails (_req, _res, _next) {
    if(_data.sessions.findSession(_req.params.guid) !== false){
        var _result = _data.users.findUser(_req.params.userName);
        if(_result === false){
            _res.send({status: 'error'});
            return;
        }
        _res.send({status: 'success', userDetails: {givenName: _result.givenName, surname: _result.surname}});
    } else {
        _res.send({status: 'noAuth'});
    }
    return _next();
}
exports.getUserDetails = getUserDetails;

function loginUser(_req, _res, _next) {
    if(_data.users.authUser(_req.body.userName, _req.body.userPassword)){
        var _newSession = _data.sessions.newSession(_req.body.userName);
        _res.send({status: 'success', guid: _newSession.guid});
    } else {
        _res.send({status: 'error'});
    }
    return _next();
}
exports.loginUser = loginUser;

function logoutUser(_req, _res, _next) {
    _data.sessions.removeSession(_req.params.guid);
    _res.send({status: 'success'});
    return _next();
}
exports.logoutUser = logoutUser;