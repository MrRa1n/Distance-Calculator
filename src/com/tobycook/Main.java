package com.tobycook;

import com.google.gson.JsonObject;
import com.tobycook.city.Cities;
import com.tobycook.city.RouteInformation;
import com.tobycook.city.TravelDetails;
import com.tobycook.web.MapsApi;

import java.util.*;

public class Main {

    // TODO: use distance formula to find out distance
    public static void main(String[] args) {
        RouteInformation routeInformation = new RouteInformation();
        TravelDetails travelDetails = new TravelDetails();
        MapsApi api = new MapsApi();

        Scanner scanner = new Scanner(System.in);

        // possibly load in a dictionary of world cities
        System.out.print("Origin city: ");
        String origin = travelDetails.readCityName();

        System.out.print("Destination city: ");
        String destination = travelDetails.readCityName();

        String travelMode = travelDetails.readTravelMode();

        JsonObject apiResponse = api.distanceMatrixApiRequest(origin, destination, travelMode);





        HashMap<String, double[]> cityStore = new HashMap<>();
        Cities cities = new Cities();
        System.out.print("Please enter the name of the first city: ");
        String firstCity = cities.readCityName(cityStore);

        System.out.print("Please enter the name of the second city: ");
        String secondCity = cities.readCityName(cityStore);

        double distance = cities.calculateDistance(cityStore, firstCity, secondCity);

        System.out.printf("%.2f km\n", distance);
        System.out.printf("%.2f mi\n", distance * 0.62137);

    }


}
