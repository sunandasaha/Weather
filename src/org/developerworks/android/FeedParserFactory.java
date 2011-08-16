package org.developerworks.android;

import weather.jinn.com.RSSWeatherXMLHandler;

public abstract class FeedParserFactory {
	// static String feedUrl = "http://www.androidster.com/android_news.rss";

	public static FeedParser getParser(String feedURL){
		return new RSSWeatherXMLHandler(feedURL);

	}
}
