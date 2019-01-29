package com.tobycook;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tobycook.city.Route;
import com.tobycook.web.MapsApi;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        run(args);
    }

    public static void run(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Read origin city
        System.out.print("Origin city: ");
        String origin = scanner.nextLine();

        // Read destination city
        System.out.print("Destination city: ");
        String destination = scanner.nextLine();

        // Read mode of travel
        String travelMode = readTravelMode();

        // Make a call to distance matrix api
        JsonObject apiResponse = new MapsApi().distanceMatrixApiRequest(origin, destination, travelMode);

        // iterate over the json response from api
        origin = apiResponse.get("origin_addresses").getAsString();
        destination = apiResponse.get("destination_addresses").getAsString();
        JsonArray rows = (JsonArray) apiResponse.get("rows");
        JsonObject rowsObj = (JsonObject) rows.get(0);
        JsonArray elements = (JsonArray) rowsObj.get("elements");
        JsonObject elementsObj = (JsonObject) elements.get(0);
        JsonObject distance = (JsonObject) elementsObj.get("distance");
        JsonObject duration = (JsonObject) elementsObj.get("duration");

        // Create new instance of Route and store parsed data
        Route route = new Route();

        try {
            route.setOrigin(origin);
            route.setDestination(destination);
            route.setDistanceInKm(distance.get("value").getAsDouble()/1000);
            route.setDistanceInMiles((distance.get("value").getAsDouble()/1000) / 1.609);
            route.setModeOfTransport(travelMode);
            route.setDuration(duration.get("value").getAsInt() / 3600);
        } catch (NullPointerException e) {
            System.out.println("Error: No results available for that route");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Print formatted information about travel
        route.print();

        // Check if user wants to check another route, exit if not
        System.out.print("Would you like to check another route? (Y/N) ");
        String input = scanner.nextLine().toUpperCase();

        if (input.equals("Y")) {
            run(args);
        } else if (input.equals("N")){
            System.exit(0);
        } else {
            System.out.println("Invalid command... exiting");
            System.exit(0);
        }
    }

    private static String readTravelMode() {
        Scanner scanner = new Scanner(System.in);
        // Read in mode from user and convert to lower case
        System.out.print("Mode of Travel (Driving, Walking, Bicycling, Transit): ");
        String travelMode = scanner.nextLine();
        travelMode = travelMode.toLowerCase();
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
}
