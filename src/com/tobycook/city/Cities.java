package com.tobycook.city;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tobycook.web.MapsApi;

import java.util.HashMap;
import java.util.Scanner;

public class Cities {
    /**
     *
     * @param cities
     * @return
     */
    public String readCityName(HashMap<String, double[]> cities) {
        MapsApi mapsApi = new MapsApi();
        Scanner scanner = new Scanner(System.in);
        // read name of city from command line
        String cityName = scanner.nextLine();
        // make request to api and store response
        String responseContent = mapsApi.MapsApiConnection(cityName);
        // store coordinates of the city
        double[] coordinates = getCoordinatesOfCity(responseContent);
        // add city name and it's coordinates to HashMap
        cities.put(cityName, coordinates);

        return cityName;
    }

    /**
     *
     * @param mapsApiResponse
     * @return
     */
    private double[] getCoordinatesOfCity(String mapsApiResponse) {
        // parse the response string into json
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(mapsApiResponse);

        // convert into a json object
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        // convert results into JsonArray
        JsonArray jsonArray = jsonObject.getAsJsonArray("results");

        // work through the tree until lat and long are reached
        // could possibly be simplified using loop
        jsonObject = jsonArray.get(0).getAsJsonObject();
        jsonObject = jsonObject.get("geometry").getAsJsonObject();
        jsonObject = jsonObject.get("location").getAsJsonObject();

        double[] coordinates = new double[2];

        // get lat and long values and store as double
        coordinates[0] = jsonObject.get("lat").getAsDouble();
        coordinates[1] = jsonObject.get("lng").getAsDouble();

        // return values as double so that math can be performed
        return coordinates;
    }

    /**
     *
     * @param cities
     * @param firstCity
     * @param secondCity
     * @return
     */
    // Calculate the distance between two cities from Google Maps using Haversine formula
    // Giving a 'as-the-crow-flies' distance between two cities
    // Source: http://www.movable-type.co.uk/scripts/latlong.html
    public double calculateDistance(HashMap<String, double[]> cities, String firstCity, String secondCity) {

        // Radius of earth in KM
        int earthRadius = 6371;

        double lat1, lat2, lon1, lon2,
                latDifference, lonDifference, a, c, distance;

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
        a = Math.sin(latDifference/2)  * Math.sin(latDifference/2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(lonDifference/2) * Math.sin(lonDifference/2);

        c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        distance = Math.round(earthRadius * c);

        return distance;
    }
}