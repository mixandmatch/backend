package de.metafinanz.mixnmatch.backend.model;

public class Location {
   private String label;

   public Location(String label) {
      super();
      this.label = label;
   }

   public void setLabel(String label) {
      this.label = label;
   }

   public String getLabel() {
      return label;
   }
}
