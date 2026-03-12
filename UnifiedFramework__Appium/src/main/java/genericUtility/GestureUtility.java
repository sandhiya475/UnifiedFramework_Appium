package genericUtility;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class GestureUtility {

    AppiumDriver driver;
    JavascriptExecutor js;

    public GestureUtility(AppiumDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    // CLICK BY ELEMENT
    public void clickByElement(WebElement element) {
        js.executeScript("mobile:clickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()));
    }

    // CLICK BY COORDINATES
    public void clickByCoordinates(int x, int y) {
        js.executeScript("mobile:clickGesture",
                ImmutableMap.of("x", x, "y", y));
    }

    // DOUBLE CLICK
    public void doubleClickByElement(WebElement element) {
        js.executeScript("mobile:doubleClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()));
    }

    public void doubleClickByCoordinates(int x, int y) {
        js.executeScript("mobile:doubleClickGesture",
                ImmutableMap.of("x", x, "y", y));
    }

    // LONG CLICK
    public void longClickByElement(WebElement element) {
        js.executeScript("mobile:longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()));
    }

    public void longClickByCoordinates(int x, int y) {
        js.executeScript("mobile:longClickGesture",
                ImmutableMap.of("x", x, "y", y));
    }

    // SCROLL (ANDROID SPECIFIC)
    public void scrollElement(String text) {
        if (driver instanceof AndroidDriver) {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
        }
    }

    // SWIPE
    public void swipeElement(WebElement element, String direction, int percent) {
        js.executeScript("mobile:swipeGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "direction", direction,
                        "percent", percent));
    }

    // DRAG AND DROP BY COORDINATES
    public void dragAndDropByCoordinates(int sx, int sy, int ex, int ey, int speed) {

        js.executeScript("mobile:dragGesture",
                ImmutableMap.of(
                        "startX", sx,
                        "startY", sy,
                        "endX", ex,
                        "endY", ey,
                        "speed", speed));
    }

    // DRAG AND DROP BY ELEMENT
    public void dragAndDropByElement(WebElement element, int x, int y, int speed) {

        js.executeScript("mobile:dragGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "endX", x,
                        "endY", y,
                        "speed", speed));
    }

    // ALERT (iOS specific)
    public void alertAccept() {

        if (driver instanceof IOSDriver) {

            Map<String, Object> param = new HashMap<>();
            param.put("action", "accept");

            js.executeScript("mobile:alert", param);
        }
    }

    // TAP
    public void tap(int x, int y) {

        Map<String, Object> param = new HashMap<>();
        param.put("x", x);
        param.put("y", y);

        js.executeScript("mobile:tap", param);
    }

    public void tap(WebElement element) {

        Map<String, Object> param = new HashMap<>();
        param.put("element", ((RemoteWebElement) element).getId());

        js.executeScript("mobile:tap", param);
    }

    public void doubleTap(int x,int y) {
		Map<String, Object> param=new HashMap<>();
		param.put("X", x);
		param.put("Y", y);
		js.executeScript("mobile:doubleTap", param);
	}
	
	public void touchAndHold(WebElement element,float duration) {
		Map<String, Object> param=new HashMap<>();
		param.put("element", ((RemoteWebElement)element).getId());
		param.put("duration", duration);
		js.executeScript("mobile:touchAndHold", param);
	}
	
	public void drag(int startX,int startY,int endX,int endY) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence dragAndDrop = new Sequence(finger, 1);
		
        dragAndDrop.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        dragAndDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragAndDrop.addAction(finger.createPointerMove(Duration.ofMillis(600),PointerInput.Origin.viewport(),endX,endY));
		dragAndDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		
		driver.perform(Collections.singleton(dragAndDrop));
	}
	
    // PINCH OPEN
    public void pinchOpen(WebElement element) {

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        Sequence seq1 = new Sequence(finger1, 0);
        Sequence seq2 = new Sequence(finger2, 0);

        int centerX = element.getRect().getX() + element.getRect().getWidth() / 2;
        int centerY = element.getRect().getY() + element.getRect().getHeight() / 2;

        seq1.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY));
        seq2.addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY));

        seq1.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        seq2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        seq1.addAction(finger1.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), centerX - 100, centerY));
        seq2.addAction(finger2.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), centerX + 100, centerY));

        seq1.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        seq2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(seq1, seq2));
    }

    // PINCH CLOSE
    public void pinchClose(WebElement element) {

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        Sequence seq1 = new Sequence(finger1, 0);
        Sequence seq2 = new Sequence(finger2, 0);

        int centerX = element.getRect().getX() + element.getRect().getWidth() / 2;
        int centerY = element.getRect().getY() + element.getRect().getHeight() / 2;

        seq1.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX - 100, centerY));
        seq2.addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX + 100, centerY));

        seq1.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        seq2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        seq1.addAction(finger1.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), centerX, centerY));
        seq2.addAction(finger2.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), centerX, centerY));

        seq1.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        seq2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(seq1, seq2));
    }
    
    public void scroll(String direction) {
        Map<String, Object> param = new HashMap<>();
        param.put("direction", direction);
        js.executeScript("mobile: scroll", param);
 	}
 	
 	// Scroll till particular element
     public void scrollToParticularElement(String predicate) {
    	By targetElement = AppiumBy.iOSNsPredicateString(predicate);

 	int i = 0;
 	int maxSwipe = 10;
 	while (i++ < maxSwipe) {

 	    if (!driver.findElements(targetElement).isEmpty()) {
             WebElement ele = driver.findElement(targetElement);
             if (ele.isDisplayed()) {
 	            System.out.println("element found");
 	            break;
 	        }
 	    }
 	}
 	    int width = driver.manage().window().getSize().getWidth();
 	    int height = driver.manage().window().getSize().getHeight();

 	    int startX = width / 2;
 	    int startY = (int) (height * 0.75);

 	    int endX = width / 2;
 	    int endY = (int) (height * 0.25);

 	    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

 	    Sequence swipe = new Sequence(finger, 1);

 	    swipe.addAction(finger.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),startX, startY));
 	    swipe.addAction( finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
         swipe.addAction(finger.createPointerMove(Duration.ofMillis(500),PointerInput.Origin.viewport(),endX,endY));
         swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
         driver.perform(Arrays.asList(swipe));
         try {
         	Thread.sleep(100);
         }catch(Exception e){
         }
 	}
 	
 	public void swipe(String direction) {
          Map<String, Object> param = new HashMap<>();
          param.put("direction", direction);
          js.executeScript("mobile: swipe", param);
 	}
 	
 	public void swipe(int startX, int startY, int endX, int endY) {

 	    int width = driver.manage().window().getSize().getWidth();
 	    int height = driver.manage().window().getSize().getHeight();

 	    startX = width / 2;
 	    startY = (int) (height * 0.75);

 	    endX = width / 2;
 	    endY = (int) (height * 0.25);

 	    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

 	    Sequence swipe = new Sequence(finger, 1);

 	    swipe.addAction(finger.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),startX,startY));

 	    swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

 	    swipe.addAction(finger.createPointerMove(Duration.ofMillis(500),PointerInput.Origin.viewport(),endX,endY));

 	    swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

 	    driver.perform(Arrays.asList(swipe));
 	}
}