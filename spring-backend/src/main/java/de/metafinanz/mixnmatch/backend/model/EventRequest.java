package de.metafinanz.mixnmatch.backend.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import de.metafinanz.mixnmatch.backend.rest.CustomDateDeserializer;
import de.metafinanz.mixnmatch.backend.rest.CustomDateSerializer;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class EventRequest {

   private String id;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   private String locationKey;

   private Date date;

   private String userid;
   private String url;
   private String matchUrl;

   public String getLocationKey() {
      return locationKey;
   }

   public void setLocationKey(String locationKey) {
      this.locationKey = locationKey;
   }

   @JsonSerialize(using = CustomDateSerializer.class)
   public Date getDate() {
      return date;
   }

   @JsonDeserialize(using = CustomDateDeserializer.class)
   public void setDate(Date date) {
      this.date = date;
   }

   public String getUserid() {
      return userid;
   }

   public void setUserid(String userid) {
      this.userid = userid;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public String getUrl() {
      return url;
   }

   public void setMatchUrl(String matchUrl) {
      this.matchUrl = matchUrl;
   }

   public String getMatchUrl() {
      return matchUrl;
   }

}
