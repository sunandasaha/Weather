package weather.jinn.com;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
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
		
		// the inside of this needs to be replaced by a function in WeatherDisplayController:updateDisplay()
		if (wo != null) {
			// setTxt
			TextView LocationTextView, CurrentDateTimeTextView, ConditionTextView, TempFCTextView,
				HumidityTextView, WindTextView;
			ImageView ConditionIconImageView;
			TextView ForecastIconImageView[] = null;
			TextView ForecastDoWTextView[] = null;
			TextView ForecastConditionTextView[] = null;
			TextView ForecastHLTextView[] = null;
			
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
			
			for (int x = 0; x < wo.wfc.size(); x++){
				ForecastIconImageView[x] = (TextView) findViewById(R.id.ForecastIconImageView);
				ForecastIconImageView[x].setText(wo.wfc.get(x).getIconImgPath());
			}
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