package weather.jinn.com;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.developerworks.android.FeedParser;
import org.developerworks.android.FeedParserFactory;
import org.developerworks.android.Message;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

public class Weather extends Activity {
    /** Called when the activity is first created. */
	
	RSSWeatherObject wo = new RSSWeatherObject();
	public String location_String;
	
    String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
    
    private static final int[] ForecastIconImageViews = {
        R.id.ForecastIconImageView0,
        R.id.ForecastIconImageView1,
        R.id.ForecastIconImageView2,
        R.id.ForecastIconImageView3
    };
    
    private static final int[] ForecastDoWTextViews = {
        R.id.ForecastDoWTextView0,
        R.id.ForecastDoWTextView1,
        R.id.ForecastDoWTextView2,
        R.id.ForecastDoWTextView3
    };
    
    private static final int[] ForecastConditionTextViews = {
        R.id.ForecastConditionTextView0,
        R.id.ForecastConditionTextView1,
        R.id.ForecastConditionTextView2,
        R.id.ForecastConditionTextView3
    };

    private static final int[] ForecastHLTextViews = {
        R.id.ForecastHLTextView0,
        R.id.ForecastHLTextView1,
        R.id.ForecastHLTextView2,
        R.id.ForecastHLTextView3
    };
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
              // Called when a new location is found by the network location provider.
              // makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
          };

        // Register the listener with the Location Manager to receive location updates
        // locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        
        location_String = "02130";
          
		// URL rssWeather = "http://www.rssweather.com/zipcode/" + location_String + "/rss.php";
		String feedURL = "http://www.rssweather.com/zipcode/" + location_String + "/rss.php";
		// String sourceUrl = "http://www.google.com/ig/api?weather=02806";
		RSSWeatherXMLHandler myXMLHandler = new RSSWeatherXMLHandler(feedURL);
		List<Message> myMessageList;
		
		// later on pull input from location provider or text
		location_String = "02130";
        
		try {
			/** Handling XML */
			/** Send URL to parse XML Tags */
	    	FeedParser parser = FeedParserFactory.getParser(feedURL);
	    	Log.i("WeatherAPP", "Feed URL=" + feedURL);
	    	long start = System.currentTimeMillis();
	    	myMessageList = parser.parse();
	    	long duration = System.currentTimeMillis() - start;
	    	Log.i("WeatherAPP", "Parser duration=" + duration);
		} catch (Exception e) {
			Log.v("WeatherAPP", "XML Parsing Exception = " + e);
		}
		
		for (int x = 0; x < myMessageList.length(); x++){
			myMessageList.
		}
		
		// the inside of this needs to be replaced by a function in WeatherDisplayController:updateDisplay()
		if (wo != null) {
			// setTxt
			/*TextView LocationTextView, CurrentDateTimeTextView, ConditionTextView, TempFCTextView,
				HumidityTextView, WindTextView;
			ImageView ConditionIconImageView;

			SimpleDateFormat CurrentDateTimeDisplayFormat = new SimpleDateFormat("EEEE yyyy-MM-dd HH:mm");
			
			LocationTextView = (TextView) findViewById(R.id.LocationTextView);
			LocationTextView.setText((CharSequence)wo.getCity());
			
			CurrentDateTimeTextView = (TextView) findViewById(R.id.CurrentDateTimeTextView);
			CurrentDateTimeTextView.setText((CharSequence)CurrentDateTimeDisplayFormat.format(wo.getCurrent_date_time()));
			
			ConditionTextView = (TextView) findViewById(R.id.ConditionTextView);
			ConditionTextView.setText((CharSequence)wo.getCondition_data());
			
			TempFCTextView = (TextView) findViewById(R.id.TempFCTextView);
			TempFCTextView.setText(Integer.toString(wo.getTemp_f())+ "°F" + "/" + Integer.toString(wo.getTemp_c())+ "°C");
			
			HumidityTextView = (TextView) findViewById(R.id.HumidityTextView);
			HumidityTextView.setText((CharSequence)wo.getHumidity());
	
			URL ConditionIconImageViewURL = null;
			try {
				ConditionIconImageViewURL = new URL("http://www.google.com" + wo.getIcon());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConditionIconImageView = (ImageView) findViewById(R.id.ConditionIconImageView);
			ConditionIconImageView.setImageBitmap(getImagefromURL(ConditionIconImageViewURL));
			
			WindTextView = (TextView) findViewById(R.id.WindTextView);
			WindTextView.setText((CharSequence)(wo.getWind_condition()));
			
			URL ForecastIconImageViewURL = null;
			
			ImageView ForecastIconImageView[] = new ImageView[4];
			TextView ForecastDoWTextView[] = new TextView[4];
			TextView ForecastConditionTextView[] = new TextView[4];
			TextView ForecastHLTextView[] = new TextView[4];			
			for (int x = 0; x < wo.wfc.size(); x++){
				try {
					ForecastIconImageViewURL = new URL ("http://www.google.com" + wo.wfc.get(x).getIconImgPath());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ForecastIconImageView[x] = (ImageView) findViewById(ForecastIconImageViews[x]);
				ForecastIconImageView[x].setImageBitmap(getImagefromURL(ForecastIconImageViewURL));
				
				ForecastDoWTextView[x] = (TextView) findViewById(ForecastDoWTextViews[x]);
				ForecastDoWTextView[x].setText(wo.wfc.get(x).getDay_of_week());
				
				ForecastConditionTextView[x] = (TextView) findViewById(ForecastConditionTextViews[x]);
				ForecastConditionTextView[x].setText(wo.wfc.get(x).getCondition());
				
				ForecastHLTextView[x] = (TextView) findViewById(ForecastHLTextViews[x]);
				ForecastHLTextView[x].setText("L:" + wo.wfc.get(x).getLow() + "°F H:" + wo.wfc.get(x).getHigh() + "°F");
			}*/
		}
		else{
			Log.v("WeatherAPP", "wo empty again...");
		}
    }
    
	public Bitmap getImagefromURL(URL imageURL){
        Bitmap bm = null; 
        try { 
            URLConnection conn = imageURL.openConnection(); 
            conn.connect(); 
            InputStream is = conn.getInputStream(); 
            BufferedInputStream bis = new BufferedInputStream(is); 
            bm = BitmapFactory.decodeStream(bis); 
            bis.close(); 
            is.close(); 
       } catch (IOException e) { 
           Log.e("WeatherAPP", "Error getting bitmap", e); 
       } 
       return bm; 
    }
}