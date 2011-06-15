package weather.jinn.com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class WeatherForecastInfo {
	String city, postal_code;
	double latitude, longitude;
	Date forecast_date, current_time;
	String unit_system;
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

	public String getCity() {
		return city;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public Date getForecast_date() {
		return forecast_date;
	}

	public Date getCurrent_time() {
		return current_time;
	}

	public String getUnit_system() {
		return unit_system;
	}
	
	WeatherForecastInfo(){};
		
	public WeatherForecastInfo nlToWFI(NodeList nl){
		WeatherForecastInfo ret_wfi = new WeatherForecastInfo();
		
		for (int x = 0; x < nl.getLength(); x++){
			Element elem = (Element)nl.item(x);
			String elemTagName = elem.getTagName();
			if (elemTagName == "city") {
				ret_wfi.city = elem.getAttribute("data");
			}
			else if (elemTagName == "postal_code") {
				ret_wfi.postal_code = elem.getAttribute("data");
			}
			else if(elemTagName == "latitude") {
				ret_wfi.latitude = Double.parseDouble(elem.getAttribute("data"));
			}
			else if(elemTagName == "longitude") {
				ret_wfi.longitude = Double.parseDouble(elem.getAttribute("data"));
			}
			else if(elemTagName == "forecast_date") {
				try {
					ret_wfi.forecast_date = (Date)formatter.parse(elem.getAttribute("data"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(elemTagName == "current_time") {
				try {
					ret_wfi.current_time = (Date)formatter.parse(elem.getAttribute("data"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(elemTagName == "unit_system") {
				ret_wfi.unit_system = elem.getAttribute("data");
			}
		}
		return ret_wfi;
	}


}
