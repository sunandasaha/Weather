package weather.jinn.com;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {
	public WeatherObject weather;
	public WeatherForecastCond forecast_conditions;
	private boolean parsing_forecast_condititions = false;
	
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

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		
		String attr = attributes.getValue("data");
		
		if (localName.equals("weather")) {
			weather = new WeatherObject();
		}
		else if (localName.equals("forecast_conditions")){
			forecast_conditions = new WeatherForecastCond(); 
			parsing_forecast_condititions = true;
		}
		else if (parsing_forecast_condititions){
			if (localName.equals("condition")){
				forecast_conditions.setCondition(attr);
			}
			else if (localName.equals("icon")) {
				forecast_conditions.setIconImgPath(attr);
			}
			else if (localName.equals("day_of_week")) {
				forecast_conditions.setDay_of_week(attr);
			}
			else if (localName.equals("low")) {
				forecast_conditions.setLow(Integer.parseInt(attr));
			}
			else if (localName.equals("high")) {
				forecast_conditions.setHigh(Integer.parseInt(attr));
			}
		}
		else if (!parsing_forecast_condititions){
			if (localName.equals("city")) {
				weather.setCity(attr);
			}
			else if (localName.equals("postal_code")) {
				weather.setPostal_code(attr);
			}
			else if (localName.equals("current_date_time")){
				SimpleDateFormat CurrentDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
				try {
					weather.setCurrent_date_time(CurrentDateTimeFormat.parse(attr));
				} catch (ParseException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (localName.equals("condition")) {
				weather.setCondition_data(attr);
			}
			else if (localName.equals("temp_f")) {
				weather.setTemp_f(Integer.parseInt(attr));
			}
			else if (localName.equals("temp_c")) {
				weather.setTemp_c(Integer.parseInt(attr));
			}
			else if (localName.equals("humidity")) {
				weather.setHumidity(attr);
			}
			else if (localName.equals("wind_condition")) {
				weather.setWind_condition(attr);
			}
			else if (localName.equals("icon")) {
				weather.setIcon(attr);
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		
		if (localName.equals("forecast_conditions")){
			weather.wfc.add(forecast_conditions);
			forecast_conditions = null;
			parsing_forecast_condititions = false;
		}
	}
}
