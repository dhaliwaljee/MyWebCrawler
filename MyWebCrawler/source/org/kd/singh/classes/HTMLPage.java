package org.kd.singh.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.kd.singh.tags.Anchor;

public class HTMLPage implements HTMLPageInterface{

	static Pattern patternAnchorTag = Pattern.compile(HTML_A_TAG_PATTERN);
	static Pattern patternAnchorLink = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
	
	ArrayList<String> inputLine = null;
	String url = "";
	

	URLConnection url_to_find = null;

	public HTMLPage() throws MalformedURLException, IOException {
	}
	
	public HTMLPage(String url) throws MalformedURLException, IOException {
		this.url = url;
		url_to_find = new URL(url).openConnection();
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
			}else if(tags==TypeHtmlTag.TITLE){
				Pattern patternTitle = Pattern.compile(HTML_TITLE_TAG_PATTERN);
				Matcher matcher = patternTitle.matcher(line);
				while(matcher.find()){
					list.add(matcher.group(1));
				}
			}
		
		return list;
	}

	@Override
	public String getTitle() {
		return null;
	}

	@Override
	public String getMetaData() {
		ArrayList list;
		String title = null;
		try {
			list = getTags(TypeHtmlTag.TITLE, null);
			title = list.get(0).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return title;
	}
	
}
