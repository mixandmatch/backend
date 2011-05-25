package de.metafinanz.mixnmatch.backend.model;

import java.math.BigDecimal;

public class Coords {
	private BigDecimal lat;
	
	private BigDecimal lon;
	
	

	public Coords(BigDecimal lat, BigDecimal lon) {
		super();
		this.lat = lat;
		this.lon = lon;
	}
	
	public Coords(){
		
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLon() {
		return lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}
	
	
}
