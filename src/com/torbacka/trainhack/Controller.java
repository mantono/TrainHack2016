package com.torbacka.trainhack;

import java.io.IOException;
import java.text.ParseException;

import com.mantono.webserver.WebPage;
import com.mantono.webserver.rest.Resource;
import com.mantono.webserver.rest.Response;
import com.mantono.webserver.rest.Verb;
import com.torbacka.trainhack.model.Rss;

public class Controller
{
	@Resource("/test")
	public static Response test0()
	{
		return new WebPage("<html><body>HEJ!<body></html>");
	}
	
	@Resource("/test/%id")
	public static Response test1(final int id)
	{
		return new WebPage("<html><body>HEJ "+id+"!<body></html>");
	}
	
	@Resource("/stop/%id")
	public static Response dataForStop(final int id) throws IOException, ParseException
	{
		final Rss rssData = TraficDataCollector.getTraficDataAsRss(id);
		return new XmlResponse(rssData.toXml());
	}
	
	@Resource("/stop/%stopStart/dest/%stopDest")
	public static Response dataForStop(final int stopStart, final int stopDest) throws IOException, ParseException
	{
		final Rss rssData = TraficDataCollector.getTraficDataAsRss(stopStart, stopDest);
		return new XmlResponse(rssData.toXml());
	}
}
