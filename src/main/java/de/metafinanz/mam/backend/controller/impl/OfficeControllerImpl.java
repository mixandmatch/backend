package de.metafinanz.mam.backend.controller.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.metafinanz.mam.backend.controller.LocationsController;
import de.metafinanz.mam.backend.controller.OfficeController;
import de.metafinanz.mam.backend.repository.Office;

public class OfficeControllerImpl implements OfficeController {

	static Logger logger = LoggerFactory.getLogger(OfficeControllerImpl.class);

	public List<Office> getLocations() {
		return Office.findAllOffices();
	}

	@Override
	public Office getLocation(Long aLocationID) {
		// TODO Handle locationID not found
		logger.trace("entering getLocation");
		Office aLocation = Office.findOffice(aLocationID);
		logger.trace("return find Location result: " + aLocation);
		return aLocation;
	}

	@Override
	public Office addLocation(Office aLocation) {
		logger.trace("entering addLocation");
		logger.debug("Adding new location with name {}", aLocation.getName());
		Office newLocation = new Office();
		newLocation.setName(aLocation.getName());
		newLocation.setLatitude(aLocation.getLatitude());
		newLocation.setLongitude(aLocation.getLongitude());
		newLocation.setAddress(aLocation.getAddress());
		newLocation.setCity(aLocation.getCity());
		newLocation.setPostalCode(aLocation.getPostalCode());
		newLocation.persist();
		List<Office> result = Office.findOfficesByName(aLocation.getName()).getResultList();
		if (result == null || result.size() > 1 || result.size() == 0) {
			throw new IllegalArgumentException("Invalid location name.");
		} else {
			return result.get(0);
		}
	}

    @Override
    public boolean removeLocation(long id) {
        Office location = Office.findOffice(id);
        if (location != null) {
            location.remove();
            return true;
        }
        return false;
    }

}