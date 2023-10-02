package Tests;

import Screens.HomeScreen;
import Screens.LoginScreen;
import Utiliti.Spreadsheet;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestBase {
    UiAutomator2Options option;
    public AppiumDriver driver;

    @BeforeClass
    public void RunAndroidApp() {
        option = new UiAutomator2Options();
        option.setDeviceName(System.getProperty("deviceName"));
        option.setPlatformName(System.getProperty("platformName"));
        option.setAutomationName(System.getProperty("androidAutomationName"));
        option.setAppActivity(System.getProperty("androidAppActivity"));
        option.setAppPackage(System.getProperty("androidAppPackage"));
        option.setApp(System.getProperty("user.dir") + "/src/resources/App/Android-MyDemoAppRN.1.3.0.build-244.apk");

    }

    @BeforeMethod
    public void AndroidDriver() throws IOException {
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), option);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
    }

    @AfterMethod
    public void quitAndroidDriver() {
        if (null != driver) {
            driver.quit();
        }
    }
}