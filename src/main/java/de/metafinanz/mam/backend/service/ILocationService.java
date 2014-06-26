
package de.metafinanz.mam.backend.service;
import java.util.List;

import javax.ws.rs.core.Response;

import de.metafinanz.mam.backend.repository.Location;

public interface ILocationService<T extends Location> {

	List<T> locations();

	Response deleteLocation(Long id);

	Response locationAdd(T aLocation);

	T getLocation(String id);

}
