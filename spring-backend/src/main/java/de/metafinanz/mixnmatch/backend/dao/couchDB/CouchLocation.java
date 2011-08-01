package de.metafinanz.mixnmatch.backend.dao.couchDB;

import de.metafinanz.mixnmatch.backend.model.Coords;
import de.metafinanz.mixnmatch.backend.model.Location;

public class CouchLocation extends Location {

	public CouchLocation(String label, String key, Coords location) {
		super(label, key, location);
	}

	private String _id;
	
	private String _rev;
	
	private String type = "location";

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_rev() {
		return _rev;
	}

	public void set_rev(String _rev) {
		this._rev = _rev;
	}

	public String getType() {
		return type;
	}
	
}
