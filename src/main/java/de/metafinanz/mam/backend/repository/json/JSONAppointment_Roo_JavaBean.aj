// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository.json;

import de.metafinanz.mam.backend.repository.json.JSONAppointment;
import java.util.Date;

privileged aspect JSONAppointment_Roo_JavaBean {
    
    public Long JSONAppointment.getAppointmentID() {
        return this.appointmentID;
    }
    
    public void JSONAppointment.setAppointmentID(Long appointmentID) {
        this.appointmentID = appointmentID;
    }
    
    public Date JSONAppointment.getAppointmentDate() {
        return this.appointmentDate;
    }
    
    public void JSONAppointment.setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    
    public Long JSONAppointment.getRootAppointment() {
        return this.rootAppointment;
    }
    
    public void JSONAppointment.setRootAppointment(Long rootAppointment) {
        this.rootAppointment = rootAppointment;
    }
    
    public Long JSONAppointment.getAppointmentLocation() {
        return this.appointmentLocation;
    }
    
    public void JSONAppointment.setAppointmentLocation(Long appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }
    
}
