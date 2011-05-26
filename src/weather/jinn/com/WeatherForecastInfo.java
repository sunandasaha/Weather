package weather.jinn.com;

import java.io.IOException;
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

import android.text.format.DateFormat;

public class WeatherForecastInfo {
	String location_String = "";
	public final String gWeather_URL = "http://www.google.com/ig/api?weather=" + location_String;
	public final String rssWeather_URL = "http://www.rssweather.com/zipcode/" + location_String + "/rss.php";
	
	// Forecast Information
	String city, postal_code;
	double latitude, longitude;
	Date forecast_date, current_time;
	String unit_system;
	
	// Current Conditions
	String condition_data, humidity, icon, wind_condition;
	int temp_f, temp_c;
	
	// Forecast Conditions array
	ArrayList forecast_conditions = new ArrayList();
	
	// constructors
	WeatherForecastInfo(){
		parse_gWeather_XmlFile();
	};

	// Methods
	/*	
	 *
	 */
	private void parse_gWeather_XmlFile(){
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {			
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
	
			//parse using builder to get DOM representation of the XML file
			Document dom = db.parse("http://www.google.com/ig/api?weather=02130");
			Element root = dom.getDocumentElement();
			
			NodeList nlForecastInfo = root.getElementsByTagName("forecast_information");
			for (int x = 0; x < nlForecastInfo.getLength(); x++){
				Element elem = (Element)nlForecastInfo.item(x);
				String elemTagName = elem.getTagName();
				if (elemTagName == "city") {
					this.city = elem.getAttribute("data");
				}
				else if (elemTagName == "postal_code") {
					this.postal_code = elem.getAttribute("data");
				}
				else if(elemTagName == "latitude") {
					this.latitude = Double.parseDouble(elem.getAttribute("data"));
				}
				else if(elemTagName == "longitude") {
					this.longitude = Double.parseDouble(elem.getAttribute("data"));
				}
				else if(elemTagName == "forecast_date") {
					this.forecast_date = (Date)formatter.parse(elem.getAttribute("data"));
				}
				else if(elemTagName == "current_time") {
					this.current_time = (Date)formatter.parse(elem.getAttribute("data"));
				}
				else if(elemTagName == "unit_system") {
					this.unit_system = elem.getAttribute("data");
				}	
			}
			
			NodeList nlCurrentCond = root.getElementsByTagName("current_conditions");
			for (int x = 0; x < nlCurrentCond.getLength(); x++){
				//this.city = nlCurrentCond;
			}

			NodeList nlForecastCond = root.getElementsByTagName("forecast_conditions");
			for (int x = 0; x < nlForecastCond.getLength(); x++){
				
				nlForecastCond.item(x);
			}
		} catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch(SAXException se) {
			se.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}