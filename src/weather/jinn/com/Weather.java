package weather.jinn.com;

import java.net.URL;
import java.text.DateFormat;
import java.util.Date;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Weather extends Activity {
    /** Called when the activity is first created. */
	
	WeatherObject wo = null;
    String currentDateTimeString = DateFormat.getDateInstance().format(new Date());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
        WeatherObject wo = new WeatherObject();
        
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
			XMLHandler myXMLHandler = new XMLHandler();
			xr.setContentHandler(myXMLHandler);
			xr.parse(new InputSource(sourceUrl.openStream()));
			
		} catch (Exception e) {
			System.out.println("XML Parsing Exception = " + e);
		}
		
		/** Get result from MyXMLHandler SitlesList Object */
		wo = XMLHandler.weather;
		
		// setTxt
		TextView LocationTextView;
		LocationTextView = (TextView) findViewById(R.id.LocationTextView); 
	    LocationTextView.setText((CharSequence)wo.getCity());
    }
}