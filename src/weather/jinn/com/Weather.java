package weather.jinn.com;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.developerworks.android.Message;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class Weather extends Activity {
    /** Called when the activity is first created. */
	
	String APPNAME = "Weather"; 
	RSSWeatherObject wo = new RSSWeatherObject();
	List<Message> myMessageList;
	public String location_String;
	
	HtmlCleaner cleaner = new HtmlCleaner();
	
    String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
    
    /*private static final int[] ForecastIconImageViews = {
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
    };*/
    
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
        
        // hard coded for now
        location_String = "02130";
		
		URL weatherURL = null;
		try {
			weatherURL = new URL("http", "www.rssweather.com", "/zipcode/" + location_String + "/rss.php");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpURLConnection urlConnection = null;
		try {
			urlConnection = (HttpURLConnection) weatherURL.openConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			RSSHandler handler = new RSSHandler();
			parser.parse(urlConnection.getInputStream(), handler);
			myMessageList = handler.getMessages();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		String nodeString = new String();
		String[] nodeStringArr;
		
		for (Message msg: myMessageList){
			// check category info to see how to process the information
			if (msg.getCategory().equals("Current Conditions")){
				// pick apart the content field from wo
				TagNode node = cleaner.clean(msg.getContent());
				
				nodeString = node.getText().toString();
				
				nodeString = nodeString.replace("Wind Speed", "WindSpeed");
				nodeString = nodeString.replace("Wind Direction", "WindDirection");
				nodeString = nodeString.replace("Heat Index", "HeatIndex");
				nodeString = nodeString.replace("Wind Chill", "WindChill");
				
				nodeString = nodeString.replace("  Barometer", " Barometer");
				nodeString = nodeString.replace(" MPH", "MPH");
				nodeString = nodeString.replace(" in.", "in.");
				nodeString = nodeString.replace(" (", "(");
				nodeString = nodeString.replace(": ", ":");
				nodeString = nodeString.replace("&#176;", "°");
				
				
				Log.i(APPNAME, nodeString); 
				
				nodeStringArr = nodeString.split(" ");
				for (int x = 0;  x < nodeStringArr.length; x++){
					Log.i(APPNAME, x + ": " + nodeStringArr[x]);
				}
				
				wo.setTemperature(nodeStringArr[0].substring(nodeStringArr[0].indexOf(":") + 1));
				wo.setDescription(nodeStringArr[1].substring(nodeStringArr[1].indexOf(":") + 1));
				
				wo.setHumidity(nodeStringArr[2].substring(nodeStringArr[2].indexOf(":") + 1));
				wo.setWind_speed(nodeStringArr[3].substring(nodeStringArr[3].indexOf(":") + 1));
				wo.setWind_direction(nodeStringArr[4].substring(nodeStringArr[4].indexOf(":") + 1));
				wo.setWind_speed(nodeStringArr[5].substring(nodeStringArr[5].indexOf(":") + 1));
				wo.setBarometer(nodeStringArr[6].substring(nodeStringArr[6].indexOf(":") + 1));
				wo.setDewpoint(nodeStringArr[7].substring(nodeStringArr[7].indexOf(":") + 1));
				wo.setHeat_index(nodeStringArr[8].substring(nodeStringArr[8].indexOf(":") + 1));
				wo.setWind_chill(nodeStringArr[9].substring(nodeStringArr[9].indexOf(":") + 1));
				wo.setVisibility(nodeStringArr[10].substring(nodeStringArr[10].indexOf(":") + 1));
			}
			
			if (msg.getCategory().equals("Forecast Conditions")){
				WeatherForecast wf = new WeatherForecast(); 
				String[] descriptionArray; 
				String temp_pattern, wind_pattern, 
					becoming_wind_pattern, rain_chance_pattern;

				temp_pattern = "";
				wind_pattern = " \\in the\\ "; 
				becoming_wind_pattern = "\\becoming\\ \\mph in the\\";
				rain_chance_pattern = "\\Chance of rain\\ \\percent\\";
				
				descriptionArray = msg.getDescription().split(".");
				
				for (String s : descriptionArray){
					if (s.matches(wind_pattern)){
						wf.setCurrentWind(s);
					}
					else if (s.matches(becoming_wind_pattern)){
						wf.setFutureWind(s);
					}
					else if (s.matches(rain_chance_pattern)){
						wf.setRain_chance(s);
					}
				}
				wo.wf.add(wf);
			}
		}
		
		// the inside of this needs to be replaced by a function in WeatherDisplayController:updateDisplay()
		if (wo != null) {
			// setTxt
			TextView PubDateTextView, LocationTextView;
			TextView TempDescTextView, HumidBaroTextView, DewpointTextView, WindSpdDirTextView, 
			 	WindChillHeatIndxTextView, VisibilityTextView;
			ImageView ConditionIconImageView;

			SimpleDateFormat CurrentDateTimeDisplayFormat = new SimpleDateFormat("EEEE yyyy-MM-dd HH:mm");
			
			PubDateTextView = (TextView) findViewById(R.id.PubDateTextView);
			//PubDateTextView.setText();
			
			LocationTextView = (TextView) findViewById(R.id.LocationTextView);
			//LocationTextView.setText();
			
			TempDescTextView = (TextView) findViewById(R.id.ccTempDescTextView);
			TempDescTextView.setText((CharSequence)wo.getTemperature() + " " + wo.getDescription());
			
			HumidBaroTextView = (TextView) findViewById(R.id.ccHumidBaroTextView);
			HumidBaroTextView.setText("Humidity: " + (CharSequence)wo.getHumidity() + " Pressure: " + wo.getBarometer());
			
			DewpointTextView = (TextView) findViewById(R.id.ccDewpointTextView);
			DewpointTextView.setText("Dewpoint: " + (CharSequence)wo.getDewpoint());
			
			WindSpdDirTextView = (TextView) findViewById(R.id.ccWindSpdDirTextView);
			WindSpdDirTextView.setText("Wind: " + (CharSequence)wo.getWind_speed() + " " + wo.getWind_direction());
			
			WindChillHeatIndxTextView = (TextView) findViewById(R.id.ccWindChillHeatIndxTextView);
			WindChillHeatIndxTextView.setText("Wind Chill: " + (CharSequence)wo.getWind_chill() + " Heat Index: " + wo.getHeat_index());
			
			VisibilityTextView = (TextView) findViewById(R.id.ccVisibilityTextView);
			VisibilityTextView.setText("Visibility: " + (CharSequence)wo.getVisibility());
			
			/* URL ConditionIconImageViewURL = null;
			try {
				ConditionIconImageViewURL = new URL("http://www.google.com" + wo.getIcon());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			URL ForecastIconImageViewURL = null;
			
			ImageView fcIconImageView[] = new ImageView[4];
			TextView fcDateTextView[] = new TextView[4];
			TextView fcConditionTextView[] = new TextView[4];
			TextView fcHLTextView[] = new TextView[4];			
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