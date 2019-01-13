package com.tobycook;

import com.tobycook.web.MapsApi;


import java.util.Scanner;
import com.google.gson.*;

public class Main {

    // TODO: use distance formula to find out distance
    public static void main(String[] args) {

        MapsApi mapsApi = new MapsApi();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the name of the first city: ");
        String city_1 = scanner.nextLine();

        String responseContent = mapsApi.MapsApiConnection(city_1);

        System.out.println(responseContent);

        getCoordinatesOfCities(responseContent);
    }

    private static void getCoordinatesOfCities(String mapsApiResponse) {
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

        // get lat and long values and store as double
        double latitude = jsonObject.get("lat").getAsDouble();
        double longitude = jsonObject.get("lng").getAsDouble();


        // return values as double so that math can be performed
        System.out.println(latitude + ", " + longitude);
    }
}
