package de.metafinanz.mam.backend.controller.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.metafinanz.mam.backend.controller.CanteenController;
import de.metafinanz.mam.backend.controller.LocationsController;
import de.metafinanz.mam.backend.repository.Canteen;
import de.metafinanz.mam.backend.repository.Office;

public class CanteenControllerImpl implements CanteenController {

	static Logger logger = LoggerFactory.getLogger(CanteenControllerImpl.class);

	public List<Canteen> getLocations() {
		return Canteen.findAllCanteens();
	}

	@Override
	public Canteen getLocation(Long aLocationID) {
		// TODO Handle locationID not found
		logger.trace("entering getLocation");
		Canteen aLocation = Canteen.findCanteen(aLocationID);
		logger.trace("return find Location result: " + aLocation);
		return aLocation;
	}

	@Override
	public Canteen addLocation(Canteen aLocation) {
		logger.trace("entering addLocation");
		logger.debug("Adding new location with name "
				+ aLocation.getName());
		Canteen newLocation = new Canteen();
		newLocation.setName(aLocation.getName());
		newLocation.setLatitude(aLocation.getLatitude());
		newLocation.setLongitude(aLocation.getLongitude());
		newLocation.setAddress(aLocation.getAddress());
		newLocation.setCity(aLocation.getCity());
		newLocation.setPostalCode(aLocation.getPostalCode());
		newLocation.persist();
		List<Canteen> result = Canteen.findCanteensByName(aLocation.getName()).getResultList();
		if (result == null || result.size() > 1 || result.size() == 0) {
			throw new IllegalArgumentException("Invalid location name.");
		} else {
			return result.get(0);
		}
	}

    @Override
    public boolean removeLocation(long id) {
        Canteen location = Canteen.findCanteen(id);
        if (location != null) {
            location.remove();
            return true;
        }
        return false;
    }

}