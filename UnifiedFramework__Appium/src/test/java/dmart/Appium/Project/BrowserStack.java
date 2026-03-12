package dmart.Appium.Project;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class BrowserStack {
	
@Test
public void brostackTest() throws MalformedURLException, URISyntaxException {
	String ur="sandhiyasekar_AnTfsk";
	String pass="xwwb9smysoVTrY8Wdrxj";
	DesiredCapabilities capabilities = new DesiredCapabilities();
	HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
	bstackOptions.put("userName", "sandhiyasekar_AnTfsk");
	bstackOptions.put("accessKey", "xwwb9smysoVTrY8Wdrxj");
	capabilities.setCapability("platformName", "android");
	capabilities.setCapability("appium:platformVersion", "15.0");
	capabilities.setCapability("appium:deviceName", "Samsung Galaxy S25 Ultra");
	capabilities.setCapability("appium:app", "bs://6b37e59d1434616fbd7911cde035446b9679c4cf");
	capabilities.setCapability("bstack:options", bstackOptions);
    String url = "https://"+ur+":"+pass+"@hub-cloud.browserstack.com/wd/hub";
    URL url1=new URI(url).toURL();
    AndroidDriver driver=new AndroidDriver(url1,capabilities);
	
}
}
