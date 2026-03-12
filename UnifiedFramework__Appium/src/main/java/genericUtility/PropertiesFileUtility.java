package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtility {
public String readDataFromPropertiesFile(String key) throws IOException {
	FileInputStream fis=new FileInputStream("./commonData/androidCommData.properties");
	Properties prop=new Properties();
	prop.load(fis);
	String data = prop.getProperty(key);
	return data;
}

public String readDataFromPropertiesFile1(String key) throws IOException {
	FileInputStream fis=new FileInputStream("./commonData/iosCommData.properties");
	Properties prop=new Properties();
	prop.load(fis);
	String data = prop.getProperty(key);
	return data;
}
}
