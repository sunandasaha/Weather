package org.developerworks.android;

import weather.jinn.com.RSSWeatherXMLHandler;

public abstract class FeedParserFactory {
	public static FeedParser getParser(String feedURL){
		return new RSSWeatherXMLHandler(feedURL);

	}
}
