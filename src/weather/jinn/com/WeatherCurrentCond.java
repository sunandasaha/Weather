package weather.jinn.com;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class WeatherCurrentCond {
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

	String condition_data, humidity, icon, wind_condition;
	int temp_f, temp_c;
	
	public WeatherCurrentCond nlToWCC(NodeList nl){
		WeatherCurrentCond ret_wcc = new WeatherCurrentCond();
		
		for (int x = 0; x < nl.getLength(); x++){
			Element elem = (Element)nl.item(x);
			String elemTagName = elem.getTagName();
			if (elemTagName == "condition_data") {
				ret_wcc.condition_data = elem.getAttribute("data");
			}
			else if (elemTagName == "humidity") {
				ret_wcc.humidity = elem.getAttribute("data");
			}
			else if(elemTagName == "wind_condition") {
				ret_wcc.wind_condition = elem.getAttribute("data");
			}
			else if(elemTagName == "temp_f") {
				ret_wcc.temp_f = Integer.parseInt(elem.getAttribute("data"));
			}
			else if(elemTagName == "temp_c") {
				ret_wcc.temp_c = Integer.parseInt(elem.getAttribute("data"));
			}
		}
		return ret_wcc;
	}
}

// a whole bunch of get/set methods for  this class