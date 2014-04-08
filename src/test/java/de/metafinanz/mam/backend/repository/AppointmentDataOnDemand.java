package de.metafinanz.mam.backend.repository;
import org.springframework.roo.addon.dod.RooDataOnDemand;

@RooDataOnDemand(entity = Appointment.class)
public class AppointmentDataOnDemand {

	public Appointment getNewTransientAppointment(int index) {
        Appointment obj = new Appointment();
        setAppointmentDate(obj, index);
        setAppointmentLocation(obj, index);
        setOwnerID(obj, index);
        return obj;
    }
}