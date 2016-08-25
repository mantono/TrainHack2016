package com.torbacka.trainhack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Item {
	private final String description;
	private final LocalDateTime pubdate;
	private final int guid;

	public Item(final int guid, final String date, final String time, final String description) {
		this.guid = guid;
		this.pubdate = createLocalDateTime(date, time);
		this.description = description;
	}
	
	private LocalDateTime createLocalDateTime(String date, String time) {
		final String data = date + "T" + time +"+01:00";
		return LocalDateTime.parse(data, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	}

	public String toXml(){
		final StringBuilder content = new StringBuilder();
		content.append("\t<description>"+description+"</description>\n");
		content.append("\t<pubdate>"+DateTimeFormatter.RFC_1123_DATE_TIME.format(pubdate)+"</pubdate>\n");
		content.append("\t<guid>"+guid+"</guid>\n");
		
		return content.toString();
	}
}
