package weather.jinn.com;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {

	Boolean currentElement = false;
	String currentValue = null;
	public WeatherObject weather;

	public WeatherObject getWeatherObject() {
		return weather;
	}

	public void setWeather(WeatherObject weather) {
		this.weather = weather;
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		
	}
	
	/** Called when tag starts ( ex:- <name>AndroidPeople</name> 
	 * -- <name> )*/
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		
		currentElement = true;
		
		String attr = attributes.getValue("data");
		
		if (localName.equals("weather")) {
			weather = new WeatherObject();
		}
		else if (localName.equals("city")) {
			weather.setCity(attr);
		}
		else if (localName.equals("postal_code")) {
			weather.setPostal_code(attr);
		}
		else if (localName.equals("condition")) {
			weather.setCondition_data(attr);
		}
		else if (localName.equals("temp_f")) {
			weather.setTemp_f(Integer.parseInt(attr));
		}
		else if (localName.equals("humidity")) {
			weather.setHumidity(attr);
		}
		else if (localName.equals("wind_condition")) {
			weather.setWind_condition(attr);
		}		
	}

	/** Called when tag closing ( ex:- <name>AndroidPeople</name> 
	 * -- </name> )*/
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		
		currentElement = false;
	}
}
