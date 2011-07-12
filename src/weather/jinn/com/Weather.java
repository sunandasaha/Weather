package weather.jinn.com;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Weather extends Activity {
    /** Called when the activity is first created. */
	
	WeatherObject wo = new WeatherObject();
    String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
    
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("E");
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
		XMLHandler myXMLHandler = new XMLHandler();
        
		try {
			/** Handling XML */
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();

			/** Send URL to parse XML Tags */
			// URL rssWeather = "http://www.rssweather.com/zipcode/" + location_String + "/rss.php";
			URL sourceUrl = new URL(
					"http://www.google.com/ig/api?weather=02130");

			/** Create handler to handle XML Tags ( extends DefaultHandler ) */
			xr.setContentHandler(myXMLHandler);
			xr.parse(new InputSource(sourceUrl.openStream()));
		} catch (Exception e) {
			Log.v("WeatherAPP", "XML Parsing Exception = " + e);
		}
		
		wo = myXMLHandler.getWeatherObject();
		
		// setTxt
		TextView LocationTextView, DateTimeTextView, ConditionTextView, HighLowTextView, 
			HumidityTextView, WindTextView;
		
		LocationTextView = (TextView) findViewById(R.id.LocationTextView);
		LocationTextView.setText((CharSequence)wo.getCity());
		
		DateTimeTextView = (TextView) findViewById(R.id.DateTimeTextView);
		DateTimeTextView.setText((CharSequence)sdf.format(date).toString() + ", " + currentDateTimeString);
		
		ConditionTextView = (TextView) findViewById(R.id.ConditionTextView);
		ConditionTextView.setText((CharSequence)wo.getCondition_data());
		
		HighLowTextView = (TextView) findViewById(R.id.HighLowTextView);
		HighLowTextView.setText(Integer.toString(wo.getTemp_f())+ " F");
		
		HumidityTextView = (TextView) findViewById(R.id.HumidityTextView);
		HumidityTextView.setText((CharSequence)wo.getHumidity());
		
		WindTextView = (TextView) findViewById(R.id.WindTextView);
		WindTextView.setText((CharSequence)(wo.getWind_condition()));

    }
}