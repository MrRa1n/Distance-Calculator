package com.tobycook.city;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TravelDetails {
    Scanner scanner = new Scanner(System.in);

    public String readCityName() {
        String cityName = scanner.nextLine();
        return cityName;
    }

    public String readTravelMode() {
        System.out.print("Mode of Travel: ");
        String travelMode = scanner.nextLine();

        List<String> travelModes = Arrays.asList(
                "driving",
                "walking",
                "bicycling",
                "transit"
        );

        if (!travelModes.contains(travelMode)) {
            System.out.println("Invalid travel mode");
            readTravelMode();
        }

        return travelMode;
    }
}
