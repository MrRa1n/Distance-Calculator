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
     * Read in the name of the city from the command line and store the city name and
     * make a request to he API for the coordinates. If returned, store the
     * coordinates in a HashMap
     *
     * @param cities - HashMap containing city name and its coordinates
     * @return the name of the city provided
     */
    public String readCityName(HashMap<String, double[]> cities) {
        MapsApi mapsApi = new MapsApi();
        Scanner scanner = new Scanner(System.in);
        // read name of city from command line
        String cityName = scanner.nextLine();
        // make request to api and store response
        String responseContent = mapsApi.geocodeApiRequest(cityName);
        // store coordinates of the city
        double[] coordinates = getCoordinatesOfCity(responseContent);
        // add city name and it's coordinates to HashMap
        cities.put(cityName, coordinates);

        return cityName;
    }

    /**
     * Method for converting the String API response into JSON and
     * extracting latitude and longitude
     *
     * @param mapsApiResponse is the response content given by Google Maps API
     * @return array containing latitude and longitude of provided city name
     */
    private double[] getCoordinatesOfCity(String mapsApiResponse) {
        try {
            // parse the response string into json
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(mapsApiResponse);

            // convert into a json object
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            // convert results into JsonArray
            JsonArray jsonArray = jsonObject.getAsJsonArray("results");
            // Throw Exception if no results returned
            if (jsonArray.size() == 0) {
                throw new Exception("Error: No results returned for that city name");
            }
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

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return null;
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

    public double getDistance(JsonObject apiResponse) {
        JsonArray jsonArray = apiResponse.getAsJsonArray("rows");
        System.out.println(jsonArray);

        apiResponse = jsonArray.get(0).getAsJsonObject();

        return 0;
    }
}
