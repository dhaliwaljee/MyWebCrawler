package org.kd.singh.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.kd.singh.tags.Anchor;

public class Crawler {

	String url = null;
	String name = "Home";

	private String test;
	HTMLPage pageObj = null;
	ArrayList<String> pageLines = null;
	URLConnection url_to_find = null;
	
	//Jsoup
	Document jDoc = null;
	Element jMetaAuthor = null;
	Element jMetaDescription = null;
	Element jMetaKeywords = null;
	
	
	public Crawler() {
	}
	
	private boolean isURLInitialized(){
		if(this.url!=null){
			return true;
		}else{
			return false;
		}
	}
	
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void getSiteMap() throws MalformedURLException, IOException{
		String robot_url = this.url+"/robots.txt";
		URLConnection robotsTxt = new URL(robot_url).openConnection();
		robotsTxt.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
		BufferedReader in = new BufferedReader(new InputStreamReader(robotsTxt.getInputStream()));
		String inputLine;
		while((inputLine = in.readLine())!=null){
//			System.out.println(inputLine);
		}
	}
	
	public void setUrl(String url) throws MalformedURLException, IOException{
		this.url = url;
		this.pageObj = new HTMLPage(this.url);
		this.pageLines = pageObj.getPage();
		
		jDoc = Jsoup.parse(pageLines.toString());
		jMetaDescription = jDoc.select("META[name=description]").first();
		jMetaAuthor = jDoc.select("META[name=author]").first();
		jMetaKeywords = jDoc.select("Meta[name=keywords]").first();
		
	}
	
	public String getKeyWords(){
		String keywords = new String();
		if(jMetaKeywords!=null){
			keywords = jMetaKeywords.attr("content");
		}
		return keywords;
	}
	
	public String getAuthor(){
		String author = new String();
		if(jMetaAuthor!=null){
			author = jMetaAuthor.attr("content");
		}
		return author;
	}

	public String getDescription(){
		String desc = new String();
		if(jMetaDescription!=null){
			desc = jMetaDescription.attr("content");
		}
		return desc;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<Anchor> getAnchorLinks() throws IOException{
		if(!isURLInitialized()){
			throw new IOException("Please call method setUrl() first");
		}
		ArrayList<Anchor> anchor_href_list = new ArrayList<Anchor>();
		
		Elements anchor_tags = jDoc.select("a");
		for(Element e: anchor_tags){
			String href = UrlHelper.modifyUrl(e.attr("href"), url);
			String label = e.html();
			Anchor temp_anchor = new Anchor(label, href);
			anchor_href_list.add(temp_anchor);
		}
		return anchor_href_list;
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getTitle() throws IOException {
		if(!isURLInitialized()){
			throw new IOException("Please call method setUrl() first"); 
		}
		return pageObj.getTitle();
	}
	
	@Deprecated
	public ArrayList<Anchor> getAnchorTag() throws IOException{
		if(!isURLInitialized()){
			throw new IOException("Please call method setUrl() first");
		}
		ArrayList<Anchor> links = new ArrayList<Anchor>();
		for(String line: pageLines){
			ArrayList<Anchor> list = HTMLPage.getTags(TypeHtmlTag.ANCHOR, line);
			links.addAll(list);
		}

		return links;
	}
		
	public String getTest(){
		return "testing";
	}
}
