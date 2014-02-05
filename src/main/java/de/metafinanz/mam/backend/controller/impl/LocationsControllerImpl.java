package de.metafinanz.mam.backend.controller.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.metafinanz.mam.backend.controller.LocationsController;
import de.metafinanz.mam.backend.repository.Location;

public class LocationsControllerImpl implements LocationsController {

	static Logger logger = LoggerFactory.getLogger(LocationsController.class);

	public List<Location> getLocations() {
		return Location.findAllLocations();
	}

	@Override
	public Location getLocation(Long aLocationID) {
		// TODO Handle locationID not found
		logger.trace("entering getLocation");
		Location aLocation = Location.findLocation(aLocationID);
		logger.trace("return find Location result: " + aLocation);
		return aLocation;
	}

	@Override
	public boolean addLocation(Location aLocation) {
		logger.trace("entering addLocation");
		logger.debug("Adding new location with name "
				+ aLocation.getLocationName());
		Location newLocation = new Location();
		newLocation.setLocationName(aLocation.getLocationName());
		newLocation.persist();
		// TODO: Error Handling
		return true;
	}

	@Override
	public boolean removeLocation(Location aLocation) {
		// TODO Auto-generated method stub
		return false;
	}

}