package weather.jinn.com;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class WeatherForecastCond {
	String day_of_week, iconImgLocation, condition;
	int low, high;
	
	public String getDay_of_week() {
		return day_of_week;
	}

	public String getIconImgLocation() {
		return iconImgLocation;
	}

	public String getCondition() {
		return condition;
	}

	public int getLow() {
		return low;
	}

	public int getHigh() {
		return high;
	}
	
	public ArrayList<WeatherForecastCond> nlToWFC(NodeList nl){
		ArrayList<WeatherForecastCond> ret_wfc = new ArrayList<WeatherForecastCond>();
		WeatherForecastCond wfc = new WeatherForecastCond();
		
		for (int x = 0; x < nl.getLength(); x++){
			Element elem = (Element)nl.item(x);
			String elemTagName = elem.getTagName();
			if (elemTagName == "day_of_week") {
				wfc.day_of_week = elem.getAttribute("data");
			}
			else if (elemTagName == "icon") {
				wfc.iconImgLocation = elem.getAttribute("data");
			}
			else if(elemTagName == "condition") {
				wfc.condition = elem.getAttribute("data");
			}
			else if(elemTagName == "low") {
				wfc.low = Integer.parseInt(elem.getAttribute("data"));
			}
			else if(elemTagName == "high") {
				wfc.high = Integer.parseInt(elem.getAttribute("data"));
			}
			
			ret_wfc.add (wfc);
		}
		return ret_wfc;
	}
}
