'use strict';

/* Data */

var _sessionModel = require('./sessionmodel.js');
var _dateModel = require('./datemodel.js');

//users
var _users = new _sessionModel.users();

_users.addUser(new _sessionModel.user('brz', 'pwd1', 'Blasius', 'Riz'));
_users.addUser(new _sessionModel.user('hg', 'pwd2', 'Hans', 'Gebell'));
_users.addUser(new _sessionModel.user('pp', 'pwd3', 'Petra', 'Prust'));
_users.addUser(new _sessionModel.user('mm', 'pwd4', 'Michael', 'Müller'));
_users.addUser(new _sessionModel.user('pw', 'pwd5', 'Peter', 'Weintraub'));
_users.addUser(new _sessionModel.user('lk', 'pwd6', 'Louise', 'Keller'));
_users.addUser(new _sessionModel.user('mf', 'pwd7', 'Maria', 'Friedrich'));
_users.addUser(new _sessionModel.user('jl', 'pwd8', 'Johann', 'Lier'));
exports.users = _users;

//sessions
var _sessions = new _sessionModel.sessions();
exports.sessions = _sessions;

//dateItems
var _dateItems = new _dateModel.dateItems();

var _now = new Date();
var _dateItem = new _dateModel.dateItem(new Date(_now.getFullYear(), _now.getMonth(), _now.getDate(), 13, 0, 0, 0).getTime(), 'Unterföhring Haus 2', _users.findUser('lk'));
_dateItem.addAttendee(_users.findUser('pp'));
_dateItem.addAttendee(_users.findUser('pw'));
_dateItems.addDateItem(_dateItem);
var _dateItem = new _dateModel.dateItem(new Date(_now.getFullYear(), _now.getMonth(), _now.getDate(), 12, 30, 0, 0).getTime(), 'Neuperlach', _users.findUser('hg'));
_dateItem.addAttendee(_users.findUser('mm'));
_dateItems.addDateItem(_dateItem);
var _dateItem = new _dateModel.dateItem(new Date(_now.getFullYear(), _now.getMonth(), _now.getDate(), 11, 30, 0, 0).getTime(), 'Kistenpfennig', _users.findUser('mf'));
_dateItem.addAttendee(_users.findUser('brz'));
_dateItem.addAttendee(_users.findUser('jl'));
_dateItems.addDateItem(_dateItem);
var _dateItem = new _dateModel.dateItem(new _dateModel.dateItem(new Date(_now.getFullYear(), _now.getMonth(), _now.getDate(), 11, 30, 0, 0).getTime() + 86400, 'E', _users.findUser('brz')));
_dateItems.addDateItem(_dateItem);
exports.dateItems = _dateItems;