package com.tobycook.city;

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

    public void print() {
        System.out.println(
                "Origin city: " + getOrigin() + "\n"
                        + "Destination city: " + getDestination() + "\n"
                        + "Distance in km: " + String.format("%.2f", getDistanceInKm()) + "\n"
                        + "Distance in miles: " + String.format("%.2f", getDistanceInMiles()) + "\n"
                        + "Mode of transport: " + getModeOfTransport() + "\n"
                        + "Journey time: " + getDuration()
        );
    }

}
