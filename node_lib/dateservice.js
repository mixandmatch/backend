'use strict';

/* Date Service */

var _ = require('underscore');
var _data = require('./data.js');
var _dateModel = require('./datemodel.js');

function fetchDateItemsByRange (_req, _res, _next) {
    if(_data.sessions.findSession(_req.params.guid) !== false){
        var _result = _data.dateItems.fetchDateItemsByRange(_req.params.dateTimeStart, _req.params.dateTimeEnd);
        if(_result === false){
            _res.send({status: 'error'});
            return _next();
        }
        _res.send({status: 'success', dateItems: _result});
    } else {
    	_res.send({status: 'noAuth'});
    }
    return _next();
}
exports.fetchDateItemsByRange = fetchDateItemsByRange;

function fetchDateItemsByOwnerName (_req, _res, _next) {
    if(_data.sessions.findSession(_req.params.guid) !== false){
        var _result = _data.dateItems.fetchDateItemsByOwnerName(_req.params.ownerName);
        if(_result === false){
            _res.send({status: 'error'});
            return _next();
        }
        _res.send({status: 'success', dateItems: _result});
    } else {
        _res.send({status: 'noAuth'});
    }
    return _next();
}
exports.fetchDateItemsByOwnerName = fetchDateItemsByOwnerName;

function addDateItem (_req, _res, _next) {
    if(_data.sessions.findSession(_req.body.guid) !== false){
        var _user = _data.users.findUser(_req.body.userName);
        if(!_.isUndefined(_user)){
            _data.dateItems.addDateItem(new _dateModel.dateItem(_req.body.dateTime, _req.body.location, _user));
            _res.send({status: 'success'});
        } else {
            _res.send({status: 'error'});
        }
    } else {
        _res.send({status: 'noAuth'});
    }
    return _next();
}
exports.addDateItem = addDateItem;

function deleteDateItem (_req, _res, _next) {
    if(_data.sessions.findSession(_req.params.guid) !== false){
        _data.dateItems.deleteDateItem(_req.params.dateGuid);
        _res.send({status: 'success'});
    } else {
        _res.send({status: 'noAuth'});
    }
    return _next();
}
exports.deleteDateItem = deleteDateItem;

function addAttendee (_req, _res, _next) {
    if(_data.sessions.findSession(_req.params.guid) !== false){
        var _dateItem = _data.dateItems.fetchDateItem(_req.params.dateGuid);
        var _user = _data.users.findUser(_req.params.userName);
        if(!_.isUndefined(_dateItem) && !_.isUndefined(_user)){
            _dateItem.addAttendee(_user);
            _res.send({status: 'success', newDateItem: _dateItem});
        } else {
            _res.send({status: 'error'});
        }
    } else {
        _res.send({status: 'noAuth'});
    }
    return _next();
}
exports.addAttendee = addAttendee;

function removeAttendee (_req, _res, _next) {
    if(_data.sessions.findSession(_req.params.guid) !== false){
        var _dateItem = _data.dateItems.fetchDateItem(_req.params.dateGuid);
        var _user = _data.users.findUser(_req.params.userName);
        if(!_.isUndefined(_dateItem) && !_.isUndefined(_user)){
            _dateItem.removeAttendee(_user);
            _res.send({status: 'success', newDateItem: _dateItem});
        } else {
            _res.send({status: 'error'});
        }
    } else {
        _res.send({status: 'noAuth'});
    }
    return _next();
}
exports.removeAttendee = removeAttendee;