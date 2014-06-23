// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository;

import de.metafinanz.mam.backend.repository.Appointment;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Appointment_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Appointment.entityManager;
    
    public static final List<String> Appointment.fieldNames4OrderClauseFilter = java.util.Arrays.asList("appointmentID", "appointmentDate", "rootAppointment", "appointmentLocation", "participants");
    
    public static final EntityManager Appointment.entityManager() {
        EntityManager em = new Appointment().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Appointment.countAppointments() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Appointment o", Long.class).getSingleResult();
    }
    
    public static List<Appointment> Appointment.findAllAppointments() {
        return entityManager().createQuery("SELECT o FROM Appointment o", Appointment.class).getResultList();
    }
    
    public static List<Appointment> Appointment.findAllAppointments(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Appointment o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Appointment.class).getResultList();
    }
    
    public static Appointment Appointment.findAppointment(Long appointmentID) {
        if (appointmentID == null) return null;
        return entityManager().find(Appointment.class, appointmentID);
    }
    
    public static List<Appointment> Appointment.findAppointmentEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Appointment o", Appointment.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Appointment> Appointment.findAppointmentEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Appointment o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Appointment.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Appointment.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Appointment.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Appointment attached = Appointment.findAppointment(this.appointmentID);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Appointment.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Appointment.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Appointment Appointment.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Appointment merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
