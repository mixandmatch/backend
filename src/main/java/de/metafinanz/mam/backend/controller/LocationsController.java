package de.metafinanz.mam.backend.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;

import de.metafinanz.mam.backend.repository.Location;

public interface LocationsController {

	/**
	 * Return all available locations.
	 * @return List of all locations stored in the DB.
	 */
	List<Location> getLocations();

	/**
	 * Return a single location with the locationID.
	 * @param aLocationID ID of the Location.
	 * @return Location from db.
	 */
	Location getLocation(Long aLocationID);

	/**
	 * Add a new location to the DB.
	 * @param aLocation Location object with the locationName.
	 * @return true if the location was successfully added, false if there was any error.
	 */
	boolean addLocation(Location aLocation);

}