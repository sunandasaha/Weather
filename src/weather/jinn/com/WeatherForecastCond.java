package weather.jinn.com;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class WeatherForecastCond {
	public String getDay_of_week() {
		return day_of_week;
	}

	public void setDay_of_week(String day_of_week) {
		this.day_of_week = day_of_week;
	}

	public String getIconImgLocation() {
		return iconImgLocation;
	}

	public void setIconImgLocation(String iconImgLocation) {
		this.iconImgLocation = iconImgLocation;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	String day_of_week, iconImgLocation, condition;
	int low, high;
	
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
