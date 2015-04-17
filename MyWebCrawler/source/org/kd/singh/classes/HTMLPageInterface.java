package org.kd.singh.classes;

import java.io.IOException;

public interface HTMLPageInterface{
	String HTML_A_TAG_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";
	String HTML_A_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";
	String HTML_TITLE_TAG_PATTERN = "\\<title>(.*)\\</title>";
	String getTitle() throws IOException;
	
}
