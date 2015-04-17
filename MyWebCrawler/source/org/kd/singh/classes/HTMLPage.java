package org.kd.singh.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.kd.singh.tags.Anchor;

public class HTMLPage implements HTMLPageInterface{

	static Pattern patternAnchorTag = Pattern.compile(HTML_A_TAG_PATTERN);
	static Pattern patternAnchorLink = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
	
	ArrayList<String> inputLine = null;
	String url = "";
	String pageTitle = "";

	HttpURLConnection url_to_find = null;
	

	public HTMLPage() throws MalformedURLException, IOException {
	}
	
	public HTMLPage(String url) throws MalformedURLException, IOException {
		this.url = url;
		//System.out.println("Error:"+url);
		url_to_find = (HttpURLConnection) new URL(url).openConnection();
		url_to_find.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
	}
	
	/**
	 * This methods is used to return all the lines have in html pages.
	 * @return StringArray All lines of html page
	 * @throws IOException
	 */
	public ArrayList<String> getPage() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(url_to_find.getInputStream()));
		String line;
		ArrayList<String> page = new ArrayList<String>();
		while((line = in.readLine())!=null){
			page.add(line);
			
			//TITLE PARSING: find title
			Pattern patternTitle = Pattern.compile(HTML_TITLE_TAG_PATTERN);
			Matcher matcher = patternTitle.matcher(line);
			while(matcher.find()){
				pageTitle = matcher.group(1);
			}
		}
		return page;
	}
	
	
	/**
	 * This method is used to return all given Tags
	 * @param tags Tag to be return
	 * @param line HTML Line of page
	 * @return ArrayList list of all tags
	 * @throws IOException
	 */
	@Deprecated
	public static ArrayList getTags(TypeHtmlTag tags, String line) throws IOException{
		ArrayList list = new ArrayList();
			if(tags == TypeHtmlTag.ANCHOR){
				
				Matcher matcher = patternAnchorTag.matcher(line);
				while(matcher.find()){
					String label = matcher.group(2);
					String href = matcher.group(1);
					
					Matcher matcher_link = patternAnchorLink.matcher(href);
					
					while(matcher_link.find()){
						String fetched_link = matcher_link.group(1).substring(1, matcher_link.group(1).length()-1);		//add fetched link in the string.
						list.add(new Anchor(label, fetched_link));
					}
				}
				return list;
			}
		
		return list;
	}

	@Override
	public String getTitle(){
		return pageTitle;
	}
	
}
