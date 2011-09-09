package weather.jinn.com;

import static org.developerworks.android.BaseFeedParser.CATEGORY;
import static org.developerworks.android.BaseFeedParser.CONTENT;
import static org.developerworks.android.BaseFeedParser.DESCRIPTION;
import static org.developerworks.android.BaseFeedParser.ITEM;
import static org.developerworks.android.BaseFeedParser.LINK;
import static org.developerworks.android.BaseFeedParser.PUB_DATE;
import static org.developerworks.android.BaseFeedParser.TITLE;

import java.util.ArrayList;
import java.util.List;

import org.developerworks.android.Message;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class RSSHandler extends DefaultHandler{
	private List<Message> messages;
	private Message currentMessage;
	private StringBuilder builder;
	
	public List<Message> getMessages(){
		return this.messages;
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		builder.append(new String(ch, start, length));
	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		super.endElement(uri, localName, name);
		if (this.currentMessage != null){
			if (localName.equalsIgnoreCase(TITLE)){
				currentMessage.setTitle(builder.toString());
			} else if (localName.equalsIgnoreCase(LINK)){
				currentMessage.setLink(builder.toString());
			} else if (localName.equalsIgnoreCase(DESCRIPTION)){
				currentMessage.setDescription(builder.toString());
			} else if (localName.equalsIgnoreCase(PUB_DATE)){
				currentMessage.setDate(builder.toString());
			} else if (localName.equalsIgnoreCase("encoded")){ 
				currentMessage.setContent(builder.toString());
			} else if (localName.equalsIgnoreCase(CATEGORY)){
				currentMessage.setCategory(builder.toString());				
			} else if (localName.equalsIgnoreCase(ITEM)){
				messages.add(currentMessage);
			}
			builder.setLength(0);	
		}
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		messages = new ArrayList<Message>();
		builder = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, name, attributes);
		if (localName.equalsIgnoreCase(ITEM)){
			this.currentMessage = new Message();
		}
	}
}