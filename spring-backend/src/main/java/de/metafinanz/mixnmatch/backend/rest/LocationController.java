package de.metafinanz.mixnmatch.backend.rest;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.metafinanz.mixnmatch.backend.model.Location;

@Controller
@RequestMapping("/locations")
public class LocationController {
   private Logger logger = LoggerFactory.getLogger(getClass());
   
   private List<Location> locations = new LinkedList<Location>();
   
   public LocationController() {
      locations.add(new Location("HVU Mitarbeiterrestaurant"));
      locations.add(new Location("VGU Mitarbeiterrestaurant"));
      locations.add(new Location("MACE"));
      locations.add(new Location("Kistenpfennig"));
   }
   
   @RequestMapping(method = RequestMethod.GET)
   public @ResponseBody List<Location> listLocations(){
      logger.debug( " > entering listLocations()" );
      return locations;
   }
   
   @RequestMapping(value="/nearby",method = RequestMethod.GET)
   public @ResponseBody List<Location> listNearbyLocations(@RequestParam String lat, @RequestParam String lon){
      logger.debug(" > entering listNearbyLocations({})", lat, lon);
      return locations.subList(0, 2);
   }


}
