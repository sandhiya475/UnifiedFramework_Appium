package objectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {

    AppiumDriver driver;

    // Constructor
    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Username']")
    @iOSXCUITFindBy(accessibility = "username")
    private WebElement usernameTF;


    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Password']")
    @iOSXCUITFindBy(accessibility = "password")
    private WebElement passwordTF;


    @AndroidFindBy(xpath = "//android.widget.Button[@text='Login']")
    @iOSXCUITFindBy(accessibility = "login")
    private WebElement loginBtn;


    // Actions
    public void login(String username, String password) {
        usernameTF.sendKeys(username);
        passwordTF.sendKeys(password);
        loginBtn.click();
    }


//    // Example Downcasting
//    public void platformSpecificAction() {
//
//        if (driver instanceof AndroidDriver) {
//
//            AndroidDriver androidDriver = (AndroidDriver) driver;
//            System.out.println("Android specific action");
//
//        } else if (driver instanceof IOSDriver) {
//
//            IOSDriver iosDriver = (IOSDriver) driver;
//            System.out.println("iOS specific action");
//
//        }
//    }
}