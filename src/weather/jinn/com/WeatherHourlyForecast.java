package weather.jinn.com;

public class WeatherHourlyForecast {
	String date, weather, clouds, temp, dew_point, precip_type, precip_chance, 
		relative_humidity, wind;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getClouds() {
		return clouds;
	}

	public void setClouds(String clouds) {
		this.clouds = clouds;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getDew_point() {
		return dew_point;
	}

	public void setDew_point(String dew_point) {
		this.dew_point = dew_point;
	}

	public String getPrecip_type() {
		return precip_type;
	}

	public void setPrecip_type(String precip_type) {
		this.precip_type = precip_type;
	}

	public String getPrecip_chance() {
		return precip_chance;
	}

	public void setPrecip_chance(String precip_chance) {
		this.precip_chance = precip_chance;
	}

	public String getRelative_humidity() {
		return relative_humidity;
	}

	public void setRelative_humidity(String relative_humidity) {
		this.relative_humidity = relative_humidity;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}
}
