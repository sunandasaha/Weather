package weather.jinn.com;

public class WeatherForecastCond {
	String day_of_week, iconImgPath, condition;
	int low, high;
		
	public String getDay_of_week() {
		return day_of_week;
	}

	public void setDay_of_week(String day_of_week) {
		this.day_of_week = day_of_week;
	}

	public String getIconImgPath() {
		return iconImgPath;
	}

	public void setIconImgPath(String iconImgPath) {
		this.iconImgPath = iconImgPath;
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

	WeatherForecastCond(){};
}
