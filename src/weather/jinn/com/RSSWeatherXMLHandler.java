package weather.jinn.com;

import java.util.ArrayList;
import java.util.List;

import org.developerworks.android.BaseFeedParser;
import org.developerworks.android.Message;
import org.w3c.dom.CharacterData;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;

public class RSSWeatherXMLHandler extends BaseFeedParser  {
	
	public RSSWeatherXMLHandler(String feedUrl) {
		super(feedUrl);
	}
	
	public static String getCharacterDataFromElement(Element e) {
		CharacterData cd = (CharacterData) e;
	    return cd.getData();
	}
	
	@Override
	public List<Message> parse() {
		final Message currentMessage = new Message();
		RootElement root = new RootElement("rss");
		final List<Message> messages = new ArrayList<Message>();
		Element channel = root.getChild("channel");
		Element item = channel.getChild(ITEM);

		item.setEndElementListener(new EndElementListener(){
			@Override
			public void end() {
				messages.add(currentMessage.copy());
			}
		});
		item.getChild(TITLE).setEndTextElementListener(new EndTextElementListener(){
			@Override
			public void end(String body) {
				currentMessage.setTitle(body);
			}
		});
		item.getChild(CATEGORY).setEndTextElementListener(new EndTextElementListener(){
			@Override
			public void end(String body) {
				currentMessage.setCategory(body);
			}
		});			
		item.getChild(LINK).setEndTextElementListener(new EndTextElementListener(){
			@Override
			public void end(String body) {
				currentMessage.setLink(body);
			}
		});
		item.getChild(DESCRIPTION).setEndTextElementListener(new EndTextElementListener(){
			@Override
			public void end(String body) {
				currentMessage.setDescription(body);
			}
		});
		item.getChild(CONTENT).setEndTextElementListener(new EndTextElementListener(){
			@Override
			public void end(String body) {
				// Log.i("Weather", "Content set: " + body.toString());
				currentMessage.setContent(body.toString());
			}
		});
		item.getChild(PUB_DATE).setEndTextElementListener(new EndTextElementListener(){
			@Override
			public void end(String body) {
				currentMessage.setDate(body);
			}
		});
		try {
			Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8, 
					root.getContentHandler());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return messages;
	}
}
