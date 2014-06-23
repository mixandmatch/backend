// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository;

import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.Location;
import de.metafinanz.mam.backend.repository.User;
import java.util.Date;
import java.util.Set;

privileged aspect Appointment_Roo_JavaBean {
    
    public Long Appointment.getAppointmentID() {
        return this.appointmentID;
    }
    
    public void Appointment.setAppointmentID(Long appointmentID) {
        this.appointmentID = appointmentID;
    }
    
    public Date Appointment.getAppointmentDate() {
        return this.appointmentDate;
    }
    
    public void Appointment.setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    
    public Appointment Appointment.getRootAppointment() {
        return this.rootAppointment;
    }
    
    public void Appointment.setRootAppointment(Appointment rootAppointment) {
        this.rootAppointment = rootAppointment;
    }
    
    public Location Appointment.getAppointmentLocation() {
        return this.appointmentLocation;
    }
    
    public void Appointment.setAppointmentLocation(Location appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }
    
    public Set<User> Appointment.getParticipants() {
        return this.participants;
    }
    
    public void Appointment.setParticipants(Set<User> participants) {
        this.participants = participants;
    }
    
}
