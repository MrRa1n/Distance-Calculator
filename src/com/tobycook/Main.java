package com.tobycook;

import com.tobycook.web.MapsApi;

import java.util.*;

import com.google.gson.*;

public class Main {

    // TODO: use distance formula to find out distance
    public static void main(String[] args) {
        HashMap<String, double[]> cities = new HashMap<>();

        System.out.print("Please enter the name of the first city: ");
        String firstCity = readCityName(cities);

        System.out.print("Please enter the name of the second city: ");
        String secondCity = readCityName(cities);

        double distance = calculateDistance(cities, firstCity, secondCity);

        System.out.println(distance);
    }

    private static String readCityName(HashMap<String, double[]> cities) {
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

    private static double[] getCoordinatesOfCity(String mapsApiResponse) {
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

    // Calculate the distance between two cities from Google Maps using Haversine formula
    private static double calculateDistance(HashMap<String, double[]> cities, String firstCity, String secondCity) {

        double[] firstCityCoordinates = cities.get(firstCity);
        double[] secondCityCoordinates = cities.get(secondCity);

        double x = firstCityCoordinates[0] - secondCityCoordinates[0];
        double y = firstCityCoordinates[1] - secondCityCoordinates[1];

        double distance = Math.sqrt(
                ((x*x)+(y+y))
        );
        return distance;
    }
}
