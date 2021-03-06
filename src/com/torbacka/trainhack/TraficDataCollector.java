package com.torbacka.trainhack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.torbacka.trainhack.model.Channel;
import com.torbacka.trainhack.model.Item;
import com.torbacka.trainhack.model.Rss;

/**
 * Created by Taco on 2016-08-25.
 */
public class TraficDataCollector {
    private static final String BASE_URL = "https://api.resrobot.se/v2/departureBoard?key=15708608-3e9f-46fe-a996-4d0af82de95e&maxJourneys=50&passlist=0&format=json&id=";
    private static final String LOOKUP_URL = "https://api.resrobot.se/v2/location.name?key=ebffa80d-b41b-4469-96f4-31d90c73f790&format=json&&input=";
    private static final String KEY = "15708608-3e9f-46fe-a996-4d0af82de95e";

    public static Rss getTraficDataAsRss(int stopId) throws IOException, ParseException {
        URL oracle = new URL(BASE_URL + stopId);
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
        List<Item> items = new ArrayList<Item>();
        if (departures.isArray()) {
            for (JsonNode node : departures) {
                System.out.println(node.get("stopid").asText());
                final String title = node.get("direction").asText();
                final String desc = node.get("name").asText();
                final String date = node.get("date").asText();
                final String time = node.get("time").asText();
                final JsonNode url = node.get("Product").get("operatorUrl");
                URL guid;
                if (url != null) {
                    guid = new URL(node.get("Product").get("operatorUrl").asText());
                } else {
                    guid = new URL("http://samtrafiken.se");
                }
                final Item item = new Item(guid, date, time, title, desc);
                items.add(item);
            }
        }

        final Channel channel = new Channel("En fin titel", "http://google.se", items);
        Rss rss = new Rss("2.0", channel);

        in.close();
        return rss;
    }

    public static Rss getTraficDataAsRss(int stopStart, String stopDest) throws IOException, ParseException {


        URL oracle = new URL(BASE_URL + stopStart);
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
        List<Item> items = new ArrayList<Item>();
        if (departures.isArray()) {
            for (JsonNode node : departures) {
                System.out.println(node.get("stopid").asText());
                final String title = node.get("direction").asText();

                final String desc = node.get("name").asText();
                final String date = node.get("date").asText();
                final String time = node.get("time").asText();
                final JsonNode url = node.get("Product").get("operatorUrl");
                URL guid;
                if (url != null) {
                    guid = new URL(node.get("Product").get("operatorUrl").asText());
                } else {
                    guid = new URL("http://samtrafiken.se");
                }

                if (!title.toLowerCase().contains(stopDest.toLowerCase())) {
                	System.out.println("\""+title+"\"" + " is not a match"); 
                    continue;
                }
                else
                	System.out.println("\t**** \""+title+"\"" + " MATCHES ****");
                final Item item = new Item(guid, date, time, title, desc);
                items.add(item);
            }
        }

        //System.out.println(departure.size());

        final Channel channel = new Channel("En fin titel", "http://google.se", items);
        Rss rss = new Rss("2.0", channel);

        in.close();
        return rss;
    }
}
