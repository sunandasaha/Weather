package weather.jinn.com;

public class WeatherForecast {
	String title, pubDate, description, temperature, current_wind, future_wind, precip_chance;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getCurrentWind() {
		return current_wind;
	}

	public void setCurrentWind(String current_wind) {
		this.current_wind = current_wind;
	}
	
	public String getFutureWind() {
		return future_wind;
	}

	public void setFutureWind(String future_wind) {
		this.future_wind = future_wind;
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
