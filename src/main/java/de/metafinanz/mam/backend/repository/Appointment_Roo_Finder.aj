// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository;

import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.Location;
import de.metafinanz.mam.backend.repository.User;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Appointment_Roo_Finder {
    
    public static TypedQuery<Appointment> Appointment.findAppointmentsByAppointmentLocation(Location appointmentLocation) {
        if (appointmentLocation == null) throw new IllegalArgumentException("The appointmentLocation argument is required");
        EntityManager em = Appointment.entityManager();
        TypedQuery<Appointment> q = em.createQuery("SELECT o FROM Appointment AS o WHERE o.appointmentLocation = :appointmentLocation", Appointment.class);
        q.setParameter("appointmentLocation", appointmentLocation);
        return q;
    }
    
    public static TypedQuery<Appointment> Appointment.findAppointmentsByOwnerID(User ownerID) {
        if (ownerID == null) throw new IllegalArgumentException("The ownerID argument is required");
        EntityManager em = Appointment.entityManager();
        TypedQuery<Appointment> q = em.createQuery("SELECT o FROM Appointment AS o WHERE o.ownerID = :ownerID", Appointment.class);
        q.setParameter("ownerID", ownerID);
        return q;
    }
    
}
