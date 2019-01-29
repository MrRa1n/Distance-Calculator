package com.tobycook.city;

import java.util.HashMap;

public class Cities {
    /**
     * Calculate the distance between two cities from Google Maps using Haversine formula
     * Giving a 'as-the-crow-flies' distance between two cities
     * Source: http://www.movable-type.co.uk/scripts/latlong.html
     *
     * @param cities - HashMap to store city name and coordinates
     * @param firstCity -  the name of the first city
     * @param secondCity - the name of the second city
     * @return the distance between the two cities in miles
     */
    //
    public double calculateDistance(HashMap<String, double[]> cities, String firstCity, String secondCity) {
        try {
            // Radius of earth in KM
            int earthRadius = 6371;

            double lat1, lat2, lon1, lon2, latDifference, lonDifference, a, c, distance;

            // Retrieve lat and lon for each city from HashMap
            double[] firstCityCoordinates = cities.get(firstCity);
            double[] secondCityCoordinates = cities.get(secondCity);

            // Convert the lat and lon of each city into radians
            lat1 = Math.toRadians(firstCityCoordinates[0]);
            lat2 = Math.toRadians(secondCityCoordinates[0]);
            lon1 = Math.toRadians(firstCityCoordinates[1]);
            lon2 = Math.toRadians(secondCityCoordinates[1]);

            // Get the difference of lat and lon
            latDifference = lat2-lat1;
            lonDifference = lon2-lon1;

            // Haversine formula
            a = Math.sin(latDifference/2) * Math.sin(latDifference/2) +
                Math.cos(lat1) * Math.cos(lat2) *
                Math.sin(lonDifference/2) * Math.sin(lonDifference/2);

            c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

            distance = earthRadius * c;

            return distance;
        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());
        }
        return 0;
    }
}
