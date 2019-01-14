package com.tobycook.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MapsApi {

    // TODO: read in api key from file
    public String MapsApiConnection(String cityName) {
        try {
            String outputFormat = "json";

            String apiKey = new String(Files.readAllBytes(Paths.get("api_key.txt")));

            String mapsUrl = "https://maps.googleapis.com/maps/api/geocode/"
                    +outputFormat+
                    "?address="+cityName+
                    "&key="+apiKey;


            // Build connection
            URL url = new URL(mapsUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");

            // Http response code
            int status = connection.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            String inputLine;
            StringBuffer content = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine + "\n");
            }
            in.close();


            if (status > 299) {
                System.out.println("Connection failed!\nResponse code: " + status);
            } else {
                System.out.println("Connection successful!\nResponse code: " + status);
            }

            return content.toString();

        } catch (Exception ex) {
            System.out.println("Something went wrong...\n" + ex.getMessage());
        }
        return "";
    }
}
