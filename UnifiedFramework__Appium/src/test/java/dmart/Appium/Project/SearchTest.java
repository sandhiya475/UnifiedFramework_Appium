package dmart.Appium.Project;

import org.testng.annotations.Test;
import genericUtility.BaseClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import objectRepository.Android_SearchPage;

public class SearchTest extends BaseClass {

@Test
public void searchTest() {

    Android_SearchPage sp = new Android_SearchPage((AndroidDriver) driver);

    sp.getSearchIcon().click();

    driver.findElement(AppiumBy.className("android.widget.EditText"))
          .sendKeys("laptop");

    sp.getEnterSymbol().click();

    String text = sp.getSearchedValue().getText();

    System.out.println(text + " is displayed successfully");
}
}
