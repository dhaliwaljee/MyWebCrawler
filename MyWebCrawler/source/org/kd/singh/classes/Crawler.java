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

import org.kd.singh.tags.Anchor;

public class Crawler {

	String url;
	private String test;

	URLConnection url_to_find = null;
	
	
	public Crawler() {
	}
	
	public void getSiteMap() throws MalformedURLException, IOException{
		String robot_url = this.url+"/robots.txt";
		URLConnection robotsTxt = new URL(robot_url).openConnection();
		robotsTxt.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
		BufferedReader in = new BufferedReader(new InputStreamReader(robotsTxt.getInputStream()));
		String inputLine;
		while((inputLine = in.readLine())!=null){
			System.out.println(inputLine);
		}
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public ArrayList<String> getPage() throws MalformedURLException, IOException{
		HTMLPage pageObj = new HTMLPage(this.url);
		ArrayList<String> page = pageObj.getPage();
		return page;
	}
	
	public ArrayList<String> getAnchorLinks() throws IOException{
		ArrayList<Anchor> anchor_list = getAnchorTag();
		ArrayList<String> anchor_href_list = new ArrayList<String>();
		for (Anchor a : anchor_list) {
			anchor_href_list.add(UrlHelper.modifyUrl(a.getAnchorHref(), url));
		}
		return anchor_href_list;
	}
	
	public String getTitle() throws MalformedURLException, IOException{
		URLConnection url_to_find = new URL(url).openConnection();
		url_to_find.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");

		return null;
	}
	
	public ArrayList<Anchor> getAnchorTag() throws IOException{
		ArrayList<Anchor> links = new ArrayList<Anchor>();
		ArrayList<String> page = getPage();
		for(String line: page){
			ArrayList<Anchor> list = HTMLPage.getTags(TypeHtmlTag.ANCHOR, line);
			links.addAll(list);
		}

		return links;
	}
	
	public String getTest(){
		return "testing";
	}
}
