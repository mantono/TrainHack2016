package com.torbacka.model;

/**
 * Created by Taco on 2016-08-25.
 */
public class Channel {
    String title;
    String link;
    List<Item> items;

    public Channel(String title, String link, List<Item> items) {
        this.title = title;
        this.link = link;
        this.items = items;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toXML() {
        StringBuilder builder = new StringBuilder();
        builder.append(  "<channel><title>" +title + "</title><link>" +link +"</link>");
        for(Item item : items) {
            builder.append(item.toXML());
        }
        builder.append("</channel>");
        return builder.toString();
    }
}
