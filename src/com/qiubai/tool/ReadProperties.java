package com.qiubai.tool;

import java.util.ResourceBundle;

public class ReadProperties {
	
	public static String read(String sourceName, String key) {
		try {
			return ResourceBundle.getBundle(
					"com.qiubai.properties." + sourceName).getString(key);
		} catch (Exception e) {
			return null;
		}
	}
}
