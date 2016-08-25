package com.torbacka.trainhack.model;

/**
 * Created by Taco on 2016-08-25.
 */
public class Rss {
    private String version = "2.0";
    private Channel channel;

    public Rss(String version, Channel channel ) {
        this.version = version;
        this.channel = channel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String toXml(){
    	//<xml version=\"1.0\">\n
        return "<rss version=\""+version+"\">" + channel.toXML() + "</rss>";
        //\n</xml>
    }
}
