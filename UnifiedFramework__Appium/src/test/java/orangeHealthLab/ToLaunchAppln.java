package orangeHealthLab;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ToLaunchAppln {
	public static void main(String[] args) throws MalformedURLException {
		
DesiredCapabilities dc=new DesiredCapabilities();
dc.setCapability("platformName", "andriod");
dc.setCapability("automationName", "uiautomator2");
dc.setCapability("deviceName", "Readmi A4 5G");
dc.setCapability("udid", "89f0fd14");
dc.setCapability("noReset", true);
dc.setCapability("autoGrantPermisions", true);
dc.setCapability("ignoreHiddenApiPolicyError", true);
dc.setCapability("appPackage", "in.orangehealth.patient");
dc.setCapability("appActivity", "in.orangehealth.patient.MainActivity");

URL url=new URL("http://localhost:4723");
AndroidDriver driver=new AndroidDriver(url,dc);
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Tests\"]")).click();
//driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Search for tests\"]")).click();
//WebElement searchTF = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text=\"Search tests, health checkups...\"]"));
//searchTF.sendKeys("Blood",Keys.ENTER);
//driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text=\"View Details\"])[1]")).click();
//driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Add to Cart\"]")).click();
//driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"1\"]")).click();
//driver.findElement(AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.ImageView[3]")).click();



}
}