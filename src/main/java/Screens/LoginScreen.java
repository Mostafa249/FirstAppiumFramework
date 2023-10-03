package Screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginScreen {
    private static AppiumDriver driver;
    private static final By userNameField = AppiumBy.accessibilityId("Username input field");
    private static final By passwordField = AppiumBy.accessibilityId("Password input field");
    private static final By loginBtn = AppiumBy.accessibilityId("Login button");


    public LoginScreen(AppiumDriver driver) {
        LoginScreen.driver = driver;
    }

    @Step("login with user name :{userName},password :{password}")
    public static void loginActions(String userName, String password) {
        driver.findElement(userNameField).sendKeys(userName);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginBtn).click();

    }
}
