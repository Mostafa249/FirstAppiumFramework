package Screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class HomeScreen {
    private static AppiumDriver driver;
    private static final By openMenuBtn = AppiumBy.accessibilityId("open menu");

    private static final By loginBtn = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]");

    public HomeScreen(AppiumDriver driver) {
        HomeScreen.driver = driver;
    }

    public static void homeScreenActions() {

        driver.findElement(openMenuBtn).click();
        driver.findElement(loginBtn).click();
    }
}
