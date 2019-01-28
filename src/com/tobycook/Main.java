package com.tobycook;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tobycook.city.RouteInformation;
import com.tobycook.city.TravelDetails;
import com.tobycook.web.MapsApi;

import java.util.*;

public class Main {

    // TODO: Get coordinates of city and calculate using Haversine formula
    // TODO: Add loop to store list of routes, unit exit command is provided
    public static void main(String[] args) {

        TravelDetails travelDetails = new TravelDetails();

        // Read origin city
        System.out.print("Origin city: ");
        String origin = travelDetails.readCityName();

        // Read destination city
        System.out.print("Destination city: ");
        String destination = travelDetails.readCityName();

        // Read mode of travel
        String travelMode = travelDetails.readTravelMode();

        // Make a call to distance matrix api
        JsonObject apiResponse = new MapsApi().distanceMatrixApiRequest(origin, destination, travelMode);

        // iterate over the json response from api
        JsonArray rows = (JsonArray) apiResponse.get("rows");
        JsonObject rowsObj = (JsonObject) rows.get(0);
        JsonArray elements = (JsonArray) rowsObj.get("elements");
        JsonObject elementsObj = (JsonObject) elements.get(0);
        JsonObject distance = (JsonObject) elementsObj.get("distance");
        JsonObject duration = (JsonObject) elementsObj.get("duration");


        // Create new instance of RouteInformation and store parsed data
        RouteInformation routeInformation = new RouteInformation();

        routeInformation.setOrigin(origin);
        routeInformation.setDestination(destination);
        routeInformation.setDistanceInKm(distance.get("value").getAsDouble()/1000);
        routeInformation.setDistanceInMiles((distance.get("value").getAsDouble()/1000) / 1.609);
        routeInformation.setModeOfTransport(travelMode);
        routeInformation.setDuration(duration.get("value").getAsInt());

        // Print formatted information about travel
        travelDetails.printTravelDetails(routeInformation);
    }


}
