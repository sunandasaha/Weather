package weather.jinn.com;

public class WeatherForecast {
	String description_text, lows, current_wind, future_wind, rain_chance;

	public String getDescription_text() {
		return description_text;
	}

	public void setDescription_text(String description_text) {
		this.description_text = description_text;
	}

	public String getLows() {
		return lows;
	}

	public void setLows(String lows) {
		this.lows = lows;
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
	
	
	public String getRain_chance() {
		return rain_chance;
	}

	public void setRain_chance(String rain_chance) {
		this.rain_chance = rain_chance;
	} 
		
}
