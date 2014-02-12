'use strict';

/* Date Model */

var _ = require('underscore');
var _utils = require('./utils.js');

function dateItem (_dateTime, _location, _owner) {
    //this.guid = _utils.guid();
    this.guid = _.uniqueId('dateitem_');
    this.dateTime = (_.isUndefined(_dateTime)) ? "" : _dateTime;
    this.location = (_.isUndefined(_location)) ? "" : _location;
    this.owner = (_.isUndefined(_owner)) ? "" : _owner;    
    this.attendees = [];

    this.addAttendee = function(_attendee){
        if(!_.contains(this.attendees, _attendee)){
            this.attendees.push(_attendee);
        }
    }

    this.removeAttendee = function(_attendee){
        this.attendees = _.reject(this.attendees, function(_attendeeItem){ return _attendeeItem == _attendee; });
    }    
}
exports.dateItem = dateItem;

function dateItems () {
    this.dateItemList = [];

    this.addDateItem = function(_dateItem) {
    	this.dateItemList.push(_dateItem);
    }

    this.deleteDateItem = function(_guid) {
    	this.dateItemList = _.reject(this.dateItemList, function(_dateItem){
            console.log(_dateItem.guid + " <> " + _guid);
            return _dateItem.guid == _guid;
        });

    }

    this.fetchDateItemsByRange = function(_dateTimeStart, _dateTimeEnd) {
        var _dateItems = [];
        _dateItems = _.filter(this.dateItemList, function(_dateItem){ return _dateItem.dateTime >= _dateTimeStart && _dateItem.dateTime <= _dateTimeEnd; });
        return _dateItems;
    }

    this.fetchDateItemsByOwnerName = function(_ownerName) {
        var _dateItems = [];
        _dateItems = _.filter(this.dateItemList, function(_dateItem){ return _dateItem.owner.userName == _ownerName; });
        return _dateItems;
    }

    this.fetchDateItem = function(_guid) {
        var _result = _.findWhere(this.dateItemList, {guid: _guid});
        return (_.isUndefined(_result)) ? false : _result;
    }
}
exports.dateItems = dateItems;