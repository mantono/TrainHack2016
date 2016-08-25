package com.torbacka.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.torbacka.model.Departure;
import com.torbacka.model.Rss;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Created by Taco on 2016-08-25.
 */
public class TraficDataCollector {
    private static final String BASE_URL = "https://api.resrobot.se/v2/departureBoard?key=15708608-3e9f-46fe-a996-4d0af82de95e&id=740000001&maxJourneys=5&passlist=0&format=json";
    private static final String KEY = "15708608-3e9f-46fe-a996-4d0af82de95e";
    private static final String BASE_RSS = "<?xml version=\"1.0\"?>\n" +
                    "<rss version=\"2.0\">\n" +
                    "<channel>" +
                    "<title>Avgångstavlan</title>" +
                    "<link>http://localhost/avgangstavlan?locationId=7400000001</link>" ;

    public Rss getTraficDataAsRss(int stopId) throws IOException {
        URL oracle = new URL(BASE_URL);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;
        StringBuffer output = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            output.append(inputLine);
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(output.toString());
        JsonNode departures = rootNode.get("Departure");
        if(departures.isArray()) {
            for (JsonNode node : departures) {
                System.out.println(node.get("stopid").asText());
            }
        }

        //System.out.println(departure.size());



        in.close();
        return null;
    }

    private String buildUrl(int stopId) {
        return "";
    }
}
