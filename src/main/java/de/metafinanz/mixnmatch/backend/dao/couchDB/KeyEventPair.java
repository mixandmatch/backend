package de.metafinanz.mixnmatch.backend.dao.couchDB;


public class KeyEventPair {
	private String id;
	private String[] key;
	private CouchEventRequest value;

	public CouchEventRequest getValue() {
		return value;
	}

	public void setValue(CouchEventRequest value) {
		this.value = value;
	}

	public String[] getKey() {
		return key;
	}

	public void setKey(String[] key) {
		this.key = key;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
