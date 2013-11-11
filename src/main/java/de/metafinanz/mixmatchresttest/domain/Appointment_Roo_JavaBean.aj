// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mixmatchresttest.domain;

import de.metafinanz.mixmatchresttest.domain.Appointment;
import de.metafinanz.mixmatchresttest.domain.Location;
import de.metafinanz.mixmatchresttest.domain.UserE;
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
    
    public UserE Appointment.getOwnerID() {
        return this.ownerID;
    }
    
    public void Appointment.setOwnerID(UserE ownerID) {
        this.ownerID = ownerID;
    }
    
    public Location Appointment.getAppointmentLocation() {
        return this.appointmentLocation;
    }
    
    public void Appointment.setAppointmentLocation(Location appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }
    
    public Set<UserE> Appointment.getParticipants() {
        return this.participants;
    }
    
    public void Appointment.setParticipants(Set<UserE> participants) {
        this.participants = participants;
    }
    
}
