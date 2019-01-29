package com.tobycook.web;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MapsApi {
    public JsonObject distanceMatrixApiRequest(String originCity, String destinationCity, String travelMode) {
        try {
            String apiKey = new String(Files.readAllBytes(Paths.get("api_key.txt")));
            String distanceMatrixBaseUrl = "https://maps.googleapis.com/maps/api/distancematrix/json";

            String encodedUrl = distanceMatrixBaseUrl +
                                "?origins=" + URLEncoder.encode(originCity, "UTF-8") +
                                "&destinations=" + URLEncoder.encode(destinationCity, "UTF-8") +
                                "&mode=" + URLEncoder.encode(travelMode, "UTF-8") +
                                "&unit=imperial" +
                                "&key=" + apiKey;

            URL url = new URL(encodedUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");

            // Implicitly open connection using getInputStream() and create
            // a BufferedReader on input stream and read from it
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            String inputLine;
            StringBuffer responseContent = new StringBuffer();
            while ((inputLine =  in.readLine()) != null) {
                responseContent.append(inputLine);
            }
            in.close();

            JsonElement jsonElement = new JsonParser().parse(responseContent.toString());

            return jsonElement.getAsJsonObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
