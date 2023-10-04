package Tests;

import Utiliti.Logs;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.*;

@Listeners(TestUtiliti.Listeners.class)
public class TestBase {
    UiAutomator2Options option;
    public AppiumDriver driver;

    @BeforeClass
    public void RunAndroidApp() {
        option = new UiAutomator2Options();
        Logs.info("Set device name");
        option.setDeviceName(System.getProperty("deviceName"));
        Logs.info("Set platform name");
        option.setPlatformName(System.getProperty("platformName"));
        Logs.info("Set automation name");
        option.setAutomationName(System.getProperty("androidAutomationName"));
        Logs.info("Set android activity and package");
        option.setAppActivity(System.getProperty("androidAppActivity"));
        option.setAppPackage(System.getProperty("androidAppPackage"));
        Logs.info("Set the app path");
        option.setApp(System.getProperty("user.dir") + "/src/resources/App/Android-MyDemoAppRN.1.3.0.build-244.apk");
    }

    @AfterMethod
    public void quitAndroidDriver() {
        if (null != driver) {
            driver.quit();
            Logs.info("App is closed");
        }
    }
}