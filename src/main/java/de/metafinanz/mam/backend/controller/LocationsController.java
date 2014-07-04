package de.metafinanz.mam.backend.controller;

import java.util.List;

import de.metafinanz.mam.backend.repository.Location;

public interface LocationsController<T extends Location> {

	/**
	 * Return all available locations.
	 * @param office 
	 * @return List of all locations stored in the DB.
	 */
	List<T> getLocations();

	/**
	 * Return a single location with the locationID.
	 * @param aLocationID ID of the Location.
	 * @return Location from db.
	 */
	T getLocation(Long aLocationID);

	/**
	 * Add a new location to the DB.
	 * @param aLocation Location object with the locationName.
	 * @return true if the location was successfully added, false if there was any error.
	 */
	Location addLocation(T aLocation);
	
	boolean removeLocation(long id);

}