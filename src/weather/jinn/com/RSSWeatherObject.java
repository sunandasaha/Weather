package weather.jinn.com;

import java.util.ArrayList;

public class RSSWeatherObject {  
	public String pub_date, location, description, temperature, humidity, wind_speed, 
		wind_direction, barometer, dewpoint, heat_index, wind_chill, visibility, sunrise, 
		sunset;

	public ArrayList<WeatherForecast> wf = new ArrayList<WeatherForecast>(4);

	public ArrayList<WeatherHourlyForecast> whf = new ArrayList<WeatherHourlyForecast>(4);

	// constructors
	RSSWeatherObject(){};

	public String getPub_date() {
		return pub_date;
	}

	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getWind_speed() {
		return wind_speed;
	}

	public void setWind_speed(String wind_speed) {
		this.wind_speed = wind_speed;
	}

	public String getWind_direction() {
		return wind_direction;
	}

	public void setWind_direction(String wind_direction) {
		this.wind_direction = wind_direction;
	}

	public String getBarometer() {
		return barometer;
	}

	public void setBarometer(String barometer) {
		this.barometer = barometer;
	}

	public String getDewpoint() {
		return dewpoint;
	}

	public void setDewpoint(String dewpoint) {
		this.dewpoint = dewpoint;
	}

	public String getHeat_index() {
		return heat_index;
	}

	public void setHeat_index(String heat_index) {
		this.heat_index = heat_index;
	}

	public String getWind_chill() {
		return wind_chill;
	}

	public void setWind_chill(String wind_chill) {
		this.wind_chill = wind_chill;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getSunrise() {
		return sunrise;
	}

	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}

	public String getSunset() {
		return sunset;
	}

	public void setSunset(String sunset) {
		this.sunset = sunset;
	}

	public ArrayList<WeatherForecast> getWf() {
		return wf;
	}

	public void setWf(ArrayList<WeatherForecast> wf) {
		this.wf = wf;
	}

	public ArrayList<WeatherHourlyForecast> getWhf() {
		return whf;
	}

	public void setWhf(ArrayList<WeatherHourlyForecast> whf) {
		this.whf = whf;
	}
}