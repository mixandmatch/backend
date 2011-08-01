package de.metafinanz.mixnmatch.backend.dao.couchDB;

import java.util.List;

import de.metafinanz.mixnmatch.backend.model.EventRequest;

public class RequestQueryResult {
	private int total_rows;
	private int offset;
	private List<KeyEventPair> rows;
	public int getTotal_rows() {
		return total_rows;
	}
	public void setTotal_rows(int total_rows) {
		this.total_rows = total_rows;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public List<KeyEventPair> getRows() {
		return rows;
	}
	public void setRows(List<KeyEventPair> rows) {
		this.rows = rows;
	}
	
	
}
