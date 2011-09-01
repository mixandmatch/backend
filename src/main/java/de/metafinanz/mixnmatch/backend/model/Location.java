package de.metafinanz.mixnmatch.backend.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class Location {

   private String id;

   private String label;

   private String key;

   private Coords coordinates;

   private String description;

   private String venue;

   public Coords getCoordinates() {
      return coordinates;
   }

   public void setCoordinates(Coords location) {
      this.coordinates = location;
   }

   public Location(String label, String key, Coords location, String description, String venue) {
      super();
      this.label = label;
      this.key = key;
      this.coordinates = location;
      this.setDescription(description);
      this.setVenue(venue);
   }

   public Location(String label, String key, Coords location) {
      this(label, key, location, null, null);
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public void setLabel(String label) {
      this.label = label;
   }

   public String getLabel() {
      return label;
   }

   public void setKey(String key) {
      this.key = key;
   }

   public String getKey() {
      return key;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getDescription() {
      return description;
   }

   public void setVenue(String venue) {
      this.venue = venue;
   }

   public String getVenue() {
      return venue;
   }
}
