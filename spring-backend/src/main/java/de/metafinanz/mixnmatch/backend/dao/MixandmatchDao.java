package de.metafinanz.mixnmatch.backend.dao;

import java.util.List;

import de.metafinanz.mixnmatch.backend.model.EventRequest;
import de.metafinanz.mixnmatch.backend.model.Location;

public interface MixandmatchDao {
	/**
	 * Saves the lunch request
	 * 
	 * @param pEventRequest
	 * 		Event request object
	 */
	public void saveLunchRequest(EventRequest pEventRequest);
	
	/**
	 * Returns all available lunch requests.
	 * 
	 * @return List of lunch requests
	 */
	public List<EventRequest> getAllRequests();
	
	
	/**
	 * Returns all available locations.
	 * 
	 * @return List of locations
	 */
	public List<Location> getAllLocations();
	
	/**
	 * Add a new location.
	 * 
	 * @param pLocation
	 * 		New location
	 * @return 
	 */
	public void addLocation(Location pLocation);
}
