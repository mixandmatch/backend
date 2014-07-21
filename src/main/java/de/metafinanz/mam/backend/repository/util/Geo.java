package de.metafinanz.mam.backend.repository.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Geo {

	static Logger logger = LoggerFactory.getLogger(Geo.class);

	/*
	 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	 * ::
	 */
	/* :: : */
	/* :: This routine calculates the distance between two points (given the : */
	/* :: latitude/longitude of those points). It is being used to calculate : */
	/*
	 * :: the distance between two locations using GeoDataSource (TM) prodducts
	 * :
	 */
	/* :: : */
	/* :: Definitions: : */
	/* :: South latitudes are negative, east longitudes are positive : */
	/* :: : */
	/* :: Passed to function: : */
	/* :: lat1, lon1 = Latitude and Longitude of point 1 (in decimal degrees) : */
	/* :: lat2, lon2 = Latitude and Longitude of point 2 (in decimal degrees) : */
	/* :: unit = the unit you desire for results : */
	/* :: where: 'M' is statute miles : */
	/* :: 'K' is kilometers (default) : */
	/* :: 'N' is nautical miles : */
	/*
	 * :: Worldwide cities and other features databases with latitude longitude
	 * :
	 */
	/* :: are available at http://www.geodatasource.com : */
	/* :: : */
	/* :: For enquiries, please contact sales@geodatasource.com : */
	/* :: : */
	/* :: Official Web site: http://www.geodatasource.com : */
	/* :: : */
	/* :: GeoDataSource.com (C) All Rights Reserved 2014 : */
	/* :: : */
	/*
	 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	 * ::
	 */

	/**
	 * Examples: 
	 *    getDistance(32.9697, -96.80322, 29.46786, -98.53506, "M")
	 *    getDistance(32.9697, -96.80322, 29.46786, -98.53506, "K")
	 *    getDistance(32.9697, -96.80322, 29.46786, -98.53506, "N")
	 * 
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @param unit
	 * @return
	 */
	public static double getDistance(double lat1, double lon1, double lat2,
			double lon2, Distance unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515; // miles
		dist = dist * unit.getMultiplier();

		logger.info("distance ({}, {}, {}, {}, '{}'): {}", lat1, lon1, lat2, lon2, unit.name(), dist);

		return (dist);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts decimal degrees to radians : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
	
	
	public enum Distance {
		MILES(1.0),
		KILOMETERS(1.609344),
		NAUTIC_MILES(0.8684),
		METERS(1.609344*1000);
		
		private final double multiplier;
		
		Distance(double multi) {
			multiplier = multi;
		}
		
		public double getMultiplier() {
			return multiplier;
		}
	}

}
