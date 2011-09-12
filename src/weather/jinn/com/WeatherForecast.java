package weather.jinn.com;

public class WeatherForecast {
	String date, description, temperature, wind, precip_chance;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getPrecip_chance() {
		return precip_chance;
	}

	public void setPrecip_chance(String precip_chance) {
		this.precip_chance = precip_chance;
	}
}
