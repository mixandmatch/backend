// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository;

import de.metafinanz.mam.backend.repository.Location;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Location_Roo_Finder {
    
    public static TypedQuery<Location> Location.findLocationsByLocationNameLike(String locationName) {
        if (locationName == null || locationName.length() == 0) throw new IllegalArgumentException("The locationName argument is required");
        locationName = locationName.replace('*', '%');
        if (locationName.charAt(0) != '%') {
            locationName = "%" + locationName;
        }
        if (locationName.charAt(locationName.length() - 1) != '%') {
            locationName = locationName + "%";
        }
        EntityManager em = Location.entityManager();
        TypedQuery<Location> q = em.createQuery("SELECT o FROM Location AS o WHERE LOWER(o.locationName) LIKE LOWER(:locationName)", Location.class);
        q.setParameter("locationName", locationName);
        return q;
    }
    
}