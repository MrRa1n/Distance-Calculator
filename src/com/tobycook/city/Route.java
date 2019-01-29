package com.tobycook.city;

import java.util.HashMap;

public class Route {

    private String origin;
    private String destination;
    private double distanceInKm;
    private double distanceInMiles;
    private String modeOfTransport;
    private int duration;

    public Route() {}

    private String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    private String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    private double getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(double distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    private double getDistanceInMiles() {
        return distanceInMiles;
    }

    public void setDistanceInMiles(double distanceInMiles) {
        this.distanceInMiles = distanceInMiles;
    }

    private String getModeOfTransport() {
        return modeOfTransport;
    }

    public void setModeOfTransport(String modeOfTransport) {
        this.modeOfTransport = modeOfTransport;
    }

    private int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

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

    public void print() {
        System.out.println(
                "Origin city: " + getOrigin() + "\n"
                        + "Destination city: " + getDestination() + "\n"
                        + "Distance in km: " + String.format("%.2f", getDistanceInKm()) + "\n"
                        + "Distance in miles: " + String.format("%.2f", getDistanceInMiles()) + "\n"
                        + "Mode of transport: " + getModeOfTransport() + "\n"
                        + "Journey time: " + getDuration() + " hours"
        );
    }

}
