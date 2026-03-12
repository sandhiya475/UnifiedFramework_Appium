package genericUtility;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.*;

public class BaseClass {

    public AppiumDriver driver;
    public AppiumDriverLocalService service;
    public PropertiesFileUtility putil=new PropertiesFileUtility();

    @BeforeSuite
    public void startServer() {

        File node = new File("C:\\Program Files\\nodejs\\node.exe");
        File appium = new File("C:\\Users\\DELL\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");

        service = new AppiumServiceBuilder().usingDriverExecutable(node).withAppiumJS(appium)
        		.withIPAddress("127.0.0.1").usingPort(4723).build();

        service.start();

        System.out.println("Appium Server Started");
    }

    @BeforeClass
    public void launchApp() throws IOException {

        DesiredCapabilities dc = new DesiredCapabilities();

        if (putil.readDataFromPropertiesFile("platform").equalsIgnoreCase("android")) {

            dc.setCapability("platformName", putil.readDataFromPropertiesFile("platformName"));
            dc.setCapability("automationName", putil.readDataFromPropertiesFile("automationName"));
            dc.setCapability("deviceName", putil.readDataFromPropertiesFile("deviceName"));
            dc.setCapability("udid", putil.readDataFromPropertiesFile("udid"));
            dc.setCapability("appPackage",putil.readDataFromPropertiesFile("appPackage") );
            dc.setCapability("appActivity", putil.readDataFromPropertiesFile("appActivity"));

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), dc);

        } else if (putil.readDataFromPropertiesFile("platform").equalsIgnoreCase("ios")) {

            dc.setCapability("platformName", putil.readDataFromPropertiesFile1("platformName"));
            dc.setCapability("automationName",putil.readDataFromPropertiesFile1("automationName") );
            dc.setCapability("deviceName", putil.readDataFromPropertiesFile1("deviceName"));
            dc.setCapability("udid",putil.readDataFromPropertiesFile1("udid") );
            dc.setCapability("bundleId", putil.readDataFromPropertiesFile1("bundleId"));

            driver = new IOSDriver(new URL("http://127.0.0.1:4723"), dc);
        }

        System.out.println("Application Launched");
    }

    @AfterSuite
    public void stopServer() {
        service.stop();
    }
}