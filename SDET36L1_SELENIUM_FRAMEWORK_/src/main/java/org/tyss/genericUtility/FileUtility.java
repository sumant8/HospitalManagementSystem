package org.tyss.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class FileUtility {
	private Properties properties;
	public void intiallizationPropertyFile(String filePath) {
		FileInputStream fis;
		try {
			fis=new FileInputStream(filePath);
			properties=new Properties();
			properties.load(fis);
		}catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
public String getDataFromProperty(String key) {
	return properties.getProperty(key).trim();
}
}
