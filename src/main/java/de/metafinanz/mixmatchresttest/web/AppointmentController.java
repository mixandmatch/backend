package de.metafinanz.mixmatchresttest.web;
import de.metafinanz.mixmatchresttest.domain.Appointment;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

@RequestMapping("/appointments")
@Controller
@RooWebScaffold(path = "appointments", formBackingObject = Appointment.class)
@RooWebJson(jsonObject = Appointment.class)
@RooWebFinder
public class AppointmentController {
}
