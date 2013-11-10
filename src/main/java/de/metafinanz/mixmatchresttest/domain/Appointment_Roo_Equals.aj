// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mixmatchresttest.domain;

import de.metafinanz.mixmatchresttest.domain.Appointment;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

privileged aspect Appointment_Roo_Equals {
    
    public boolean Appointment.equals(Object obj) {
        if (!(obj instanceof Appointment)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Appointment rhs = (Appointment) obj;
        return new EqualsBuilder().append(appointmentDate, rhs.appointmentDate).append(appointmentID, rhs.appointmentID).append(appointmentLocation, rhs.appointmentLocation).append(ownerID, rhs.ownerID).isEquals();
    }
    
    public int Appointment.hashCode() {
        return new HashCodeBuilder().append(appointmentDate).append(appointmentID).append(appointmentLocation).append(ownerID).toHashCode();
    }
    
}
