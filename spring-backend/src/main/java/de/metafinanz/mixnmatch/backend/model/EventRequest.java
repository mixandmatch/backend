package de.metafinanz.mixnmatch.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import de.metafinanz.mixnmatch.backend.rest.CustomDateDeserializer;
import de.metafinanz.mixnmatch.backend.rest.CustomDateSerializer;
@Entity
public class EventRequest {
    
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String id;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(unique=true, nullable=true)
	private String locationKey;
	@Column(unique=true, nullable=true)
	private Date date;
	@Column(unique=true, nullable=true)
	private String userid;
	private String url;

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

}
