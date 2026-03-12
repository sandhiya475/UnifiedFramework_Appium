package genericUtility;
 import java.util.Set;
 import org.openqa.selenium.ScreenOrientation;
 import io.appium.java_client.AppiumBy;
 import io.appium.java_client.AppiumDriver;
 import io.appium.java_client.android.AndroidDriver;
 import io.appium.java_client.appmanagement.ApplicationState;
 import io.appium.java_client.ios.IOSDriver;

    public class UnifiedDriverUtility {

        AppiumDriver driver;

        public UnifiedDriverUtility(AppiumDriver driver) {
            this.driver = driver;
        }

        // Install App
        public void installApp(String path) {

            if(driver instanceof AndroidDriver) {
                ((AndroidDriver) driver).installApp(path);
            }
            else if(driver instanceof IOSDriver) {
                ((IOSDriver) driver).installApp(path);
            }
        }

        // Launch App
        public void launchApp(String appPackage_BundleId) {

            if (driver instanceof AndroidDriver) {

                AndroidDriver androidDriver = (AndroidDriver) driver;
                androidDriver.activateApp(appPackage_BundleId);

            } else if (driver instanceof IOSDriver) {

                IOSDriver iosDriver = (IOSDriver) driver;
                iosDriver.activateApp(appPackage_BundleId);
            }
        }

        // Check App Installed
        public boolean isAppInstalled(String appPackage) {

            if(driver instanceof AndroidDriver) {
                return ((AndroidDriver) driver).isAppInstalled(appPackage);
            }
            else if(driver instanceof IOSDriver) {
                return ((IOSDriver) driver).isAppInstalled(appPackage);
            }

            return false;
        }

        // Close App
        public void closeApp(String appPackage) {

            if(driver instanceof AndroidDriver) {
                ((AndroidDriver) driver).terminateApp(appPackage);
            }
            else if(driver instanceof IOSDriver) {
                ((IOSDriver) driver).terminateApp(appPackage);
            }
        }

        // Delete App
        public void deleteApp(String appPackage) {

            if(driver instanceof AndroidDriver) {
                ((AndroidDriver) driver).removeApp(appPackage);
            }
            else if(driver instanceof IOSDriver) {
                ((IOSDriver) driver).removeApp(appPackage);
            }
        }
    

            // Open Notification (Android only)
            public void openNotification() {

                if (driver instanceof AndroidDriver) {

                    AndroidDriver androidDriver = (AndroidDriver) driver;
                    androidDriver.openNotifications();

                } 
            }
         // Toast Message for Android & iOS
            public void toastMessage() {

                if (driver instanceof AndroidDriver) {

                    AndroidDriver androidDriver = (AndroidDriver) driver;

                    String message = androidDriver
                            .findElement(AppiumBy.xpath("//android.widget.Toast")).getText();
                          

                    System.out.println("Android Toast Message : " + message);
                }

                else if (driver instanceof IOSDriver) {

                    IOSDriver iosDriver = (IOSDriver) driver;

                    String message = iosDriver
                            .findElement(AppiumBy.xpath("//XCUIElementTypeStaticText"))
                            .getText();

                    System.out.println("iOS Toast Message : " + message);
                }
            }

            // Clipboard (works differently for Android & iOS)
            public void saveInClipBoard(String message) {

                if (driver instanceof AndroidDriver) {

                    AndroidDriver androidDriver = (AndroidDriver) driver;
                    androidDriver.setClipboardText(message);

                } else if (driver instanceof IOSDriver) {

                    IOSDriver iosDriver = (IOSDriver) driver;
                    iosDriver.setClipboardText(message);
                }
            }

            public String getInClipBoard() {

                if (driver instanceof AndroidDriver) {

                    AndroidDriver androidDriver = (AndroidDriver) driver;
                    return androidDriver.getClipboardText();

                } else if (driver instanceof IOSDriver) {

                    IOSDriver iosDriver = (IOSDriver) driver;
                    return iosDriver.getClipboardText();
                }

                return null;
            }

        
            // Screen Rotation
            public void screenPortrait() {

                if (driver instanceof AndroidDriver) {
                    ((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT);
                } else if (driver instanceof IOSDriver) {
                    ((IOSDriver) driver).rotate(ScreenOrientation.PORTRAIT);
                }
            }

            public void screenLandscape() {

                if (driver instanceof AndroidDriver) {
                    ((AndroidDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
                } else if (driver instanceof IOSDriver) {
                    ((IOSDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
                }
            }


        // App Status
        public void appStatus(String appPackage) {

            if (driver instanceof AndroidDriver) {

                AndroidDriver androidDriver = (AndroidDriver) driver;
                ApplicationState state = androidDriver.queryAppState(appPackage);
                System.out.println("Android App State : " + state);

            } else if (driver instanceof IOSDriver) {

                IOSDriver iosDriver = (IOSDriver) driver;
                ApplicationState state = iosDriver.queryAppState(appPackage);
                System.out.println("iOS App State : " + state);
            }
        }

       
        // Hide Keyboard
        public void hideKeyboard() {

            if (driver instanceof AndroidDriver) {

                AndroidDriver androidDriver = (AndroidDriver) driver;
                androidDriver.hideKeyboard();

            } else if (driver instanceof IOSDriver) {

                IOSDriver iosDriver = (IOSDriver) driver;
                iosDriver.hideKeyboard();
            }
        }
        
        
     // Context Handling (Hybrid Apps) for Android & iOS
        public void contextHandles(String partialValue) throws InterruptedException {

            if (driver instanceof AndroidDriver) {
                AndroidDriver androidDriver = (AndroidDriver) driver;
                Set<String> contexts = androidDriver.getContextHandles();
                for (String ch : contexts) {
                    System.out.println("Android Context : " + ch);
                    if (ch.contains(partialValue)) {
                        androidDriver.context(ch);
                        Thread.sleep(3000);
                        break;
                    }
                }

                String title = androidDriver.getTitle();
                System.out.println("Page Title : " + title);
            }

            else if (driver instanceof IOSDriver) {
                IOSDriver iosDriver = (IOSDriver) driver;
                Set<String> contexts = iosDriver.getContextHandles();
                for (String ch : contexts) {
                    System.out.println("iOS Context : " + ch);
                    if (ch.contains(partialValue)) {
                        iosDriver.context(ch);
                        Thread.sleep(3000);
                        break;
                    }
                }
                String title = iosDriver.getTitle();
                System.out.println("Page Title : " + title);
            }
        }
    }


