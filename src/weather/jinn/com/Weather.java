package weather.jinn.com;

import android.app.Activity;
import android.os.Bundle;

public class Weather extends Activity {
    /** Called when the activity is first created. */
	
	// "http://www.google.com/ig/api?weather=" + zipcode/city name
	// http://www.rssweather.com/zipcode/02130/wx.php
	// http://www.rssweather.com/zipcode/02447/rss.php
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}