
package de.metafinanz.mam.backend.service;
import javax.ws.rs.core.Response;

import de.metafinanz.mam.backend.repository.Location;

public interface ILocationService<T extends Location> {

	Response locations();

	Response deleteLocation(Long id);

	Response locationAdd(T aLocation);

	Response getLocation(String id);

}
