package de.metafinanz.mam.backend.controller;

import java.util.List;

import de.metafinanz.mam.backend.repository.Canteen;

public interface CanteenController extends LocationsController<Canteen>{

	List<Canteen> getLocationByOffice(Long id);

}
