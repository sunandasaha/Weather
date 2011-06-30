package weather.jinn.com;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {

	Boolean currentElement = false;
	String currentValue = null;
	public static WeatherObject weather = null;

	public static WeatherObject getWeatherObject() {
		return weather;
	}

	public static void setSitesList(WeatherObject weather) {
		XMLHandler.weather = weather;
	}

	/** Called when tag starts ( ex:- <name>AndroidPeople</name> 
	 * -- <name> )*/
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		currentElement = true;

		if (localName.equals("weather")) {
			/** Start */ 
			weather = new WeatherObject();
		} 
		else if (localName.equals("website")) {
			/** Get attribute value */
			String attr = attributes.getValue("data");
			weather.setCategory(attr);
		}

	}

	/** Called when tag closing ( ex:- <name>AndroidPeople</name> 
	 * -- </name> )*/
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		currentElement = false;

		/** set value */ 
		if (localName.equalsIgnoreCase("city"))
			weather.setCity(currentValue);
		else if (localName.equalsIgnoreCase("postal_code"))
			weather.setPostalCode(currentValue);

	}

	/** Called to get tag characters ( ex:- <name>AndroidPeople</name> 
	 * -- to get AndroidPeople Character ) */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		if (currentElement) {
			currentValue = new String(ch, start, length);
			currentElement = false;
		}

	}

}
