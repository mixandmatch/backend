package de.metafinanz.mixnmatch.backend.model;

public class Location {
   private String label;
   
   private String key;

   public Location(String label, String key) {
      super();
      this.label = label;
      this.key = key;
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
}
