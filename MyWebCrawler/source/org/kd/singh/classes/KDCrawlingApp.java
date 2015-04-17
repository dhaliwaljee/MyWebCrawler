package org.kd.singh.classes;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.kd.singh.tags.Anchor;

public class KDCrawlingApp {
	ArrayList<Crawler> list = new ArrayList<Crawler>();
	private String url = null;
	private Crawler baseCrawler = null;
	
	public KDCrawlingApp() {
	}
	
	public Crawler getBasePage(){
		return this.baseCrawler;
	}
	public void setUrl(String url) throws IOException{
		ArrayList<Anchor> links = null;
		
		this.url = url;// URLEncoder.encode(url,"UTF-8");
//		System.out.println(url);
		Crawler crawl = new Crawler();
		crawl.setUrl(this.url);
		baseCrawler = crawl;
		links = crawl.getAnchorLinks();
		
		// go through the sub links of the link of page
		for(Anchor sub_links: links){
			if(sub_links.getAnchorHref().contains("javascript:void") || sub_links.getAnchorHref().contains("void")){
		//		System.out.println(sub_links);
				continue;
			}
			Crawler sub_link_page = new Crawler();
			sub_link_page.setName(sub_links.getAnchorLabel());
			sub_link_page.setUrl(sub_links.getAnchorHref());
			list.add(sub_link_page);
		}
	}
	
	public ArrayList<Crawler> getLinks(){
		return list;
	}
	
}
