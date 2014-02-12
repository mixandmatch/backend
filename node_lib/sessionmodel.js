'use strict';

/* Session Model */

var _ = require('underscore');
var _utils = require('./utils.js');

function user (_userName, _userPassword, _givenName, _surname) {
    this.userName = _userName;
    this.userPassword = (_.isUndefined(_userPassword)) ? "" : _userPassword;
    this.givenName = (_.isUndefined(_givenName)) ? "" : _givenName;
    this.surname = (_.isUndefined(_surname)) ? "" : _surname;

    this.saveClone = function() {
        var _clone = _.clone(this);
        _clone.userPassword = '';
        return _clone;
    }
}
exports.user = user;

function users () {
    this.userList = [];

    this.findUser = function(_userName) {
    	var _user = _.findWhere(this.userList, {userName: _userName});
    	return (_.isUndefined(_user)) ? false : _user;
    }

    this.authUser = function(_userName, _userPassword) {
    	var _user = _.findWhere(this.userList, {userName: _userName, userPassword: _userPassword});
    	return (_.isUndefined(_user)) ? false : true;
    }    

    this.addUser = function(_user) {
    	this.userList.push(_user);
    }

    this.removeUser = function(_userName) {
    	this.userList = _.reject(this.userList, function(_userItem){ return _userItem.userName == _userName; });
    }    
}
exports.users = users;

function session (_userName, _guid, _age) {
    this.userName = (_.isUndefined(_userName)) ? "" : _userName;
    this.guid = (_.isUndefined(_guid)) ? "" : _guid;
    this.age = (_.isUndefined(_age)) ? "" : _age;
}
exports.session = session;

function sessions () {
	this.MAX_AGE = 86400;
    this.sessionList = [];

    this.newSession = function(_userName){
    	var _now = new Date().getTime();
    	var _age = this.MAX_AGE;
		this.sessionList = _.reject(this.sessionList, function(_sessionItem){ return _sessionItem.userName == _userName; });	
		this.sessionList = _.reject(this.sessionList, function(_sessionItem){ return _sessionItem.age < _now - _age; });
        var _newSession = new session(_userName, _utils.guid(), _now);
    	this.sessionList.push(_newSession);
        return _newSession;
    }

    this.removeSession = function(_guid){
    	this.sessionList = _.reject(this.sessionList, function(_sessionItem){ return _sessionItem.guid == _guid; });
    }

    this.findSession = function(_guid){
    	var _sessionItem = _.findWhere(this.sessionList, {guid: _guid});
    	return (_.isUndefined(_sessionItem)) ? false : _sessionItem;
    }

    this.findSessionByUsername = function(_userName){
    	var _sessionItem = _.findWhere(this.sessionList, {userName: _userName});
    	return (_.isUndefined(_sessionItem)) ? false : _sessionItem;
    }
}
exports.sessions = sessions;