package de.metafinanz.mixnmatch.backend.dao;

import java.util.List;

import de.metafinanz.mixnmatch.backend.model.EventRequest;

public interface MixandmatchDao {
	/**
	 * Saves the lunch request
	 * 
	 * @param eventRequest
	 * 		Event request object
	 */
	public void saveLunchRequest(EventRequest eventRequest);
	
	/**
	 * Returns all available lunch requests.
	 * 
	 * @return List of lunch requests
	 */
	public List<EventRequest> getAllRequests();
	
}
