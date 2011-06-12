package weather.jinn.com;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class WeatherObject {
	String location_String = "";
	public final String gWeather_URL = "http://www.google.com/ig/api?weather=" + location_String;
	public final String rssWeather_URL = "http://www.rssweather.com/zipcode/" + location_String + "/rss.php";
	
	String TestURL = "http://www.google.com/ig/api?weather=02130";
	
	// Forecast Information
	WeatherForecastInfo wfi = new WeatherForecastInfo();
	
	// Current Conditions
	WeatherCurrentCond wcc = new WeatherCurrentCond();
	
	// Forecast Conditions array
	ArrayList<WeatherForecastCond> wfc = new ArrayList<WeatherForecastCond>();
	
	// constructors
	WeatherObject(){
		parse_gWeather_XmlFile();
	};

	// Methods
	/*	parse_gWeather_XmlFile
	 *
	 */
	private void parse_gWeather_XmlFile(){

		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {			
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
	
			//parse using builder to get DOM representation of the XML file
			Document dom = db.parse(TestURL);
			Element root = dom.getDocumentElement();
			
			NodeList nlForecastInfo = root.getElementsByTagName("forecast_information");
			wfi.nlToWFI(nlForecastInfo);
			
			NodeList nlCurrentCond = root.getElementsByTagName("current_conditions");
			wcc.nlToWCC(nlCurrentCond);

			NodeList nlForecastCond = root.getElementsByTagName("forecast_conditions");
			// wfc.nlToWFC(nlForecastCond);
			
		} catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch(SAXException se) {
			se.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}