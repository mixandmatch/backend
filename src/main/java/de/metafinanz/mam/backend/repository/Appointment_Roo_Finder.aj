// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository;

import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.Location;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Appointment_Roo_Finder {
    
    public static Long Appointment.countFindAppointmentsByAppointmentDateGreaterThan(Date appointmentDate) {
        if (appointmentDate == null) throw new IllegalArgumentException("The appointmentDate argument is required");
        EntityManager em = Appointment.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Appointment AS o WHERE o.appointmentDate > :appointmentDate", Long.class);
        q.setParameter("appointmentDate", appointmentDate);
        return ((Long) q.getSingleResult());
    }
    
    public static Long Appointment.countFindAppointmentsByAppointmentLocation(Location appointmentLocation) {
        if (appointmentLocation == null) throw new IllegalArgumentException("The appointmentLocation argument is required");
        EntityManager em = Appointment.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Appointment AS o WHERE o.appointmentLocation = :appointmentLocation", Long.class);
        q.setParameter("appointmentLocation", appointmentLocation);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<Appointment> Appointment.findAppointmentsByAppointmentDateGreaterThan(Date appointmentDate) {
        if (appointmentDate == null) throw new IllegalArgumentException("The appointmentDate argument is required");
        EntityManager em = Appointment.entityManager();
        TypedQuery<Appointment> q = em.createQuery("SELECT o FROM Appointment AS o WHERE o.appointmentDate > :appointmentDate", Appointment.class);
        q.setParameter("appointmentDate", appointmentDate);
        return q;
    }
    
    public static TypedQuery<Appointment> Appointment.findAppointmentsByAppointmentDateGreaterThan(Date appointmentDate, String sortFieldName, String sortOrder) {
        if (appointmentDate == null) throw new IllegalArgumentException("The appointmentDate argument is required");
        EntityManager em = Appointment.entityManager();
        String jpaQuery = "SELECT o FROM Appointment AS o WHERE o.appointmentDate > :appointmentDate";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Appointment> q = em.createQuery(jpaQuery, Appointment.class);
        q.setParameter("appointmentDate", appointmentDate);
        return q;
    }
    
    public static TypedQuery<Appointment> Appointment.findAppointmentsByAppointmentLocation(Location appointmentLocation, String sortFieldName, String sortOrder) {
        if (appointmentLocation == null) throw new IllegalArgumentException("The appointmentLocation argument is required");
        EntityManager em = Appointment.entityManager();
        String jpaQuery = "SELECT o FROM Appointment AS o WHERE o.appointmentLocation = :appointmentLocation";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Appointment> q = em.createQuery(jpaQuery, Appointment.class);
        q.setParameter("appointmentLocation", appointmentLocation);
        return q;
    }
    
}
