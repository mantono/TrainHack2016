package com.torbacka.trainhack.model;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Item {
	private final String title, description;
	private final Date pubdate;
	private final URL guid;

	public Item(final URL guid, final String date, final String time, final String title, final String desc) throws ParseException {
		this.guid = guid;
		this.pubdate = createLocalDateTime(date, time);
		this.title = title;
		this.description = desc;
	}
	
	private Date createLocalDateTime(String date, String time) throws ParseException {
		//final String data = date + "T" + time +"+01:00";
		final String data = date + " " + time;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.parse(data);
	}

	public String toXml(){
		final StringBuilder content = new StringBuilder();
		content.append("<item>\n");
		content.append("\t<title>"+title+"</title>\n");
		content.append("\t<description>"+description+"</description>\n");
		final SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
		final String date = formatter.format(pubdate);
		//final String date = DateTimeFormatter.RFC_1123_DATE_TIME.format(pubdate);
		content.append("\t<pubdate>"+date+"</pubdate>\n");
		content.append("\t<guid>"+guid+"</guid>\n");
		content.append("</item>\n");
		
		return content.toString();
	}
}
