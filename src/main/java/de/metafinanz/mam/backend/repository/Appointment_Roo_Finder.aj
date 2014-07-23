// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository;

import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.Canteen;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Appointment_Roo_Finder {
    
    public static Long Appointment.countFindAppointmentsByAppointmentDate(Date appointmentDate) {
        if (appointmentDate == null) throw new IllegalArgumentException("The appointmentDate argument is required");
        EntityManager em = Appointment.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Appointment AS o WHERE o.appointmentDate = :appointmentDate", Long.class);
        q.setParameter("appointmentDate", appointmentDate);
        return ((Long) q.getSingleResult());
    }
    
    public static Long Appointment.countFindAppointmentsByAppointmentDateGreaterThan(Date appointmentDate) {
        if (appointmentDate == null) throw new IllegalArgumentException("The appointmentDate argument is required");
        EntityManager em = Appointment.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Appointment AS o WHERE o.appointmentDate > :appointmentDate", Long.class);
        q.setParameter("appointmentDate", appointmentDate);
        return ((Long) q.getSingleResult());
    }
    
    public static Long Appointment.countFindAppointmentsByCanteen(Canteen canteen) {
        if (canteen == null) throw new IllegalArgumentException("The canteen argument is required");
        EntityManager em = Appointment.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Appointment AS o WHERE o.canteen = :canteen", Long.class);
        q.setParameter("canteen", canteen);
        return ((Long) q.getSingleResult());
    }
    
    public static Long Appointment.countFindAppointmentsByScrambledAndAppointmentDateBetween(Boolean scrambled, Date minAppointmentDate, Date maxAppointmentDate) {
        if (scrambled == null) throw new IllegalArgumentException("The scrambled argument is required");
        if (minAppointmentDate == null) throw new IllegalArgumentException("The minAppointmentDate argument is required");
        if (maxAppointmentDate == null) throw new IllegalArgumentException("The maxAppointmentDate argument is required");
        EntityManager em = Appointment.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Appointment AS o WHERE o.scrambled = :scrambled AND o.appointmentDate BETWEEN :minAppointmentDate AND :maxAppointmentDate", Long.class);
        q.setParameter("scrambled", scrambled);
        q.setParameter("minAppointmentDate", minAppointmentDate);
        q.setParameter("maxAppointmentDate", maxAppointmentDate);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<Appointment> Appointment.findAppointmentsByAppointmentDate(Date appointmentDate) {
        if (appointmentDate == null) throw new IllegalArgumentException("The appointmentDate argument is required");
        EntityManager em = Appointment.entityManager();
        TypedQuery<Appointment> q = em.createQuery("SELECT o FROM Appointment AS o WHERE o.appointmentDate = :appointmentDate", Appointment.class);
        q.setParameter("appointmentDate", appointmentDate);
        return q;
    }
    
    public static TypedQuery<Appointment> Appointment.findAppointmentsByAppointmentDate(Date appointmentDate, String sortFieldName, String sortOrder) {
        if (appointmentDate == null) throw new IllegalArgumentException("The appointmentDate argument is required");
        EntityManager em = Appointment.entityManager();
        String jpaQuery = "SELECT o FROM Appointment AS o WHERE o.appointmentDate = :appointmentDate";
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
    
    public static TypedQuery<Appointment> Appointment.findAppointmentsByCanteen(Canteen canteen, String sortFieldName, String sortOrder) {
        if (canteen == null) throw new IllegalArgumentException("The canteen argument is required");
        EntityManager em = Appointment.entityManager();
        String jpaQuery = "SELECT o FROM Appointment AS o WHERE o.canteen = :canteen";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Appointment> q = em.createQuery(jpaQuery, Appointment.class);
        q.setParameter("canteen", canteen);
        return q;
    }
    
    public static TypedQuery<Appointment> Appointment.findAppointmentsByScrambledAndAppointmentDateBetween(Boolean scrambled, Date minAppointmentDate, Date maxAppointmentDate) {
        if (scrambled == null) throw new IllegalArgumentException("The scrambled argument is required");
        if (minAppointmentDate == null) throw new IllegalArgumentException("The minAppointmentDate argument is required");
        if (maxAppointmentDate == null) throw new IllegalArgumentException("The maxAppointmentDate argument is required");
        EntityManager em = Appointment.entityManager();
        TypedQuery<Appointment> q = em.createQuery("SELECT o FROM Appointment AS o WHERE o.scrambled = :scrambled AND o.appointmentDate BETWEEN :minAppointmentDate AND :maxAppointmentDate", Appointment.class);
        q.setParameter("scrambled", scrambled);
        q.setParameter("minAppointmentDate", minAppointmentDate);
        q.setParameter("maxAppointmentDate", maxAppointmentDate);
        return q;
    }
    
    public static TypedQuery<Appointment> Appointment.findAppointmentsByScrambledAndAppointmentDateBetween(Boolean scrambled, Date minAppointmentDate, Date maxAppointmentDate, String sortFieldName, String sortOrder) {
        if (scrambled == null) throw new IllegalArgumentException("The scrambled argument is required");
        if (minAppointmentDate == null) throw new IllegalArgumentException("The minAppointmentDate argument is required");
        if (maxAppointmentDate == null) throw new IllegalArgumentException("The maxAppointmentDate argument is required");
        EntityManager em = Appointment.entityManager();
        String jpaQuery = "SELECT o FROM Appointment AS o WHERE o.scrambled = :scrambled AND o.appointmentDate BETWEEN :minAppointmentDate AND :maxAppointmentDate";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Appointment> q = em.createQuery(jpaQuery, Appointment.class);
        q.setParameter("scrambled", scrambled);
        q.setParameter("minAppointmentDate", minAppointmentDate);
        q.setParameter("maxAppointmentDate", maxAppointmentDate);
        return q;
    }
    
}
