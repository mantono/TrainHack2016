package com.torbacka.trainhack.model;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Item {
	private final String description;
	private final LocalDateTime pubdate;
	private final URL guid;

	public Item(final URL guid, final String date, final String time, final String description) {
		this.guid = guid;
		this.pubdate = createLocalDateTime(date, time);
		this.description = description;
	}
	
	private LocalDateTime createLocalDateTime(String date, String time) {
		//final String data = date + "T" + time +"+01:00";
		final String data = date + "T" + time;
		return LocalDateTime.parse(data, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

	public String toXml(){
		final StringBuilder content = new StringBuilder();
		content.append("\t<description>"+description+"</description>\n");
		final String date = pubdate.format(DateTimeFormatter.RFC_1123_DATE_TIME);
		//final String date = DateTimeFormatter.RFC_1123_DATE_TIME.format(pubdate);
		content.append("\t<pubdate>"+date+"</pubdate>\n");
		content.append("\t<guid>"+guid+"</guid>\n");
		
		return content.toString();
	}
}
