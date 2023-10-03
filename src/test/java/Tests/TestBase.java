package Tests;

import Screens.CartScreen;
import Screens.HomeScreen;
import Screens.LoginScreen;
import Screens.ProductScreen;
import Utiliti.Logs;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

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

    @BeforeMethod
    public void AndroidDriver() throws IOException {
        Logs.info("Set Android driver");
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), option);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        Logs.info("App  is opened");
        new HomeScreen(driver);
        new LoginScreen(driver);
        new ProductScreen(driver);
        new CartScreen(driver);
    }

    @AfterMethod
    public void quitAndroidDriver() {
        if (null != driver) {
            driver.quit();
            Logs.info("App is closed");
        }
    }
}