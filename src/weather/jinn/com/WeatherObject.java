package weather.jinn.com;

import java.util.ArrayList;
import java.util.Date;

public class WeatherObject {
	private String location_String = "02130";
	
	// Forecast Information
	public String city, postal_code, unit_system;
	public double latitude, longitude;
	public Date forecast_date, current_time;
	
	// Current Conditions
	public String condition_data, humidity, icon, wind_condition;
	public int temp_f, temp_c;
	
	// Forecast Conditions array
	public ArrayList<WeatherForecastCond> wfc = new ArrayList<WeatherForecastCond>();
	
	// constructors
	WeatherObject(){};

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
}