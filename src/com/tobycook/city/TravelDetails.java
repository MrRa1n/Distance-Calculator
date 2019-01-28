package com.tobycook.city;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TravelDetails {
    Scanner scanner = new Scanner(System.in);

    public String readCityName() {
        return scanner.nextLine();
    }

    public String readTravelMode() {
        // Read in mode from user and convert to lower case
        System.out.print("Mode of Travel (Driving, Walking, Bicycling, Transit): ");
        String travelMode = scanner.nextLine();
        travelMode.toLowerCase();
        // List of correct modes of travel
        List<String> travelModes = Arrays.asList(
                "driving",
                "walking",
                "bicycling",
                "transit"
        );
        // Check if provided mode is within list
        if (!travelModes.contains(travelMode)) {
            System.out.println("Invalid travel mode");
            readTravelMode();
        }
        // Return the travel mode as string
        return travelMode;
    }

    public void printTravelDetails(RouteInformation r) {
        System.out.println(
                "Origin city: " + r.getOrigin() + "\n"
                + "Destination city: " + r.getDestination() + "\n"
                + "Distance in km: " + String.format("%.2f", r.getDistanceInKm()) + "\n"
                + "Distance in miles: " + String.format("%.2f", r.getDistanceInMiles()) + "\n"
                + "Mode of transport: " + r.getModeOfTransport() + "\n"
                + "Journey time: " + r.getDuration()
        );
    }
}
