package com.tobycook;

import com.tobycook.city.Cities;
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

        System.out.println(distance +  " miles");
    }


}
