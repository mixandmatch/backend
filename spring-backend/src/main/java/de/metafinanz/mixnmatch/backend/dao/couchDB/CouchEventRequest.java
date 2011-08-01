package de.metafinanz.mixnmatch.backend.dao.couchDB;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import de.metafinanz.mixnmatch.backend.model.EventRequest;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class CouchEventRequest extends EventRequest {
	private String _id;
	
	private String _rev;
	
	private String type = "request";

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
