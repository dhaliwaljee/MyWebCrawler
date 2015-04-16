package org.kd.singh.classes;

public interface HTMLPageInterface{
	String HTML_A_TAG_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";
	String HTML_A_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";
	String HTML_TITLE_TAG_PATTERN = "\\<title>(.*)\\</title>";
	String getTitle();
	String getMetaData();
	
}
