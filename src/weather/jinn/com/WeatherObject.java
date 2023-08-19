package weather.jinn.com;

import java.util.ArrayList;
import java.util.List;

public class WeatherObject {
    private String pubDate, location, description, temperature, humidity, windSpeed,
            windDirection, barometer, dewpoint, heatIndex, windChill, visibility, sunrise,
            sunset;

    private List<WeatherForecast> forecasts = new ArrayList<>();
    private List<WeatherHourlyForecast> hourlyForecasts = new ArrayList<>();

    // Getters and setters for attributes

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
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

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
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

    public String getHeatIndex() {
        return heatIndex;
    }

    public void setHeatIndex(String heatIndex) {
        this.heatIndex = heatIndex;
    }

    public String getWindChill() {
        return windChill;
    }

    public void setWindChill(String windChill) {
        this.windChill = windChill;
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

    public List<WeatherForecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<WeatherForecast> forecasts) {
        this.forecasts = forecasts;
    }

    public List<WeatherHourlyForecast> getHourlyForecasts() {
        return hourlyForecasts;
    }

    public void setHourlyForecasts(List<WeatherHourlyForecast> hourlyForecasts) {
        this.hourlyForecasts = hourlyForecasts;
    }

    // Other methods as needed
}