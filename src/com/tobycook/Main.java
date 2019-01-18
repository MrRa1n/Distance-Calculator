package com.tobycook;

import com.tobycook.city.Cities;
import com.tobycook.web.MapsApi;

import java.util.*;

public class Main {

    // TODO: use distance formula to find out distance
    public static void main(String[] args) {
        HashMap<String, double[]> cityStore = new HashMap<>();
        Cities cities = new Cities();
        System.out.print("Please enter the name of the first city: ");
        String firstCity = cities.readCityName(cityStore);

        System.out.print("Please enter the name of the second city: ");
        String secondCity = cities.readCityName(cityStore);

        double distance = cities.calculateDistance(cityStore, firstCity, secondCity);

        System.out.printf("%.2f km\n", distance);
        System.out.printf("%.2f mi\n", distance * 0.62137);

        MapsApi api = new MapsApi();
        api.distanceMatrixApiRequest("London", "Edinburgh", "driving");
    }


}
