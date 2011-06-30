package weather.jinn.com;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class WeatherObject {
	String location_String = "02130";
	
	String city, postal_code;
	double latitude, longitude;
	Date forecast_date, current_time;
	String unit_system;
	
	// Current Conditions
	String condition_data, humidity, icon, wind_condition;
	int temp_f, temp_c;
	
	// Forecast Conditions array
	ArrayList<WeatherForecastCond> wfc = new ArrayList<WeatherForecastCond>();
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
	
	// constructors
	WeatherObject(){
		// parse_gWeather_XmlFile();
	};

	// Methods
	/*	parse_gWeather_XmlFile
	 *
	 */
	public WeatherObject parse_gWeather_XmlFile(){

		//get the factory
		WeatherObject wo = new WeatherObject();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {			
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
	
			//parse using builder to get DOM representation of the XML file
			Document dom = db.parse(gWeather_URL);
			Element root = dom.getDocumentElement();
			
			NodeList nlCurrentCond = root.getElementsByTagName("current_conditions");
			wo.wcc.nlToWCC(nlCurrentCond);

			NodeList nlForecastCond = root.getElementsByTagName("forecast_conditions");
			// wfc.nlToWFC(nlForecastCond);
			
		} catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch(SAXException se) {
			se.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return wo;
	}
	
	public String getLocation_String() {
		return location_String;
	}

	public void setLocation_String(String location_String) {
		this.location_String = location_String;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Date getForecast_date() {
		return forecast_date;
	}

	public void setForecast_date(Date forecast_date) {
		this.forecast_date = forecast_date;
	}

	public Date getCurrent_time() {
		return current_time;
	}

	public void setCurrent_time(Date current_time) {
		this.current_time = current_time;
	}

	public String getUnit_system() {
		return unit_system;
	}

	public void setUnit_system(String unit_system) {
		this.unit_system = unit_system;
	}

	public String getCondition_data() {
		return condition_data;
	}

	public void setCondition_data(String condition_data) {
		this.condition_data = condition_data;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getWind_condition() {
		return wind_condition;
	}

	public void setWind_condition(String wind_condition) {
		this.wind_condition = wind_condition;
	}

	public int getTemp_f() {
		return temp_f;
	}

	public void setTemp_f(int temp_f) {
		this.temp_f = temp_f;
	}

	public int getTemp_c() {
		return temp_c;
	}

	public void setTemp_c(int temp_c) {
		this.temp_c = temp_c;
	}

	public SimpleDateFormat getFormatter() {
		return formatter;
	}

	public void setFormatter(SimpleDateFormat formatter) {
		this.formatter = formatter;
	}

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