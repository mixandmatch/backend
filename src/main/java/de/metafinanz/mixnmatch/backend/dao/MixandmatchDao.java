package de.metafinanz.mixnmatch.backend.dao;

import java.util.Collection;
import java.util.List;

import de.metafinanz.mixnmatch.backend.model.EventRequest;
import de.metafinanz.mixnmatch.backend.model.Location;
import de.metafinanz.mixnmatch.backend.model.Match;

public interface MixandmatchDao {
	/**
	 * Saves the lunch request
	 * 
	 * @param pEventRequest
	 * 		Event request object
	 */
	public void saveRequest(EventRequest pEventRequest);
	
	/**
	 * Returns all available lunch requests.
	 * 
	 * @return List of lunch requests
	 */
	public List<? extends EventRequest> getAllRequests();
	
	
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

	public EventRequest getRequest(String locationKey, String date, String userid);

	public Collection<? extends EventRequest> getRequestsByUser(String user);
	
	public Collection<? extends EventRequest> getRequestsByLocation(String location);
	
	public Collection<? extends EventRequest> getRequestsByLocationAndDate(String location, String date);
	
	public Collection<? extends EventRequest> listAllMatches();
	
	public Collection<? extends EventRequest> listMatches(String location, String date);

	public void deleteRequest(String location, String date, String user);
}
