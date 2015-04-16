package org.kd.singh.example;

import java.io.IOException;
import java.net.MalformedURLException;

import org.kd.singh.classes.Crawler;

public class Test1 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException, IOException {
		Crawler find = new Crawler();
		try {
			//find.getLinks("http://code.org");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
