package Tests;

import Screens.HomeScreen;
import Screens.LoginScreen;
import Utiliti.Spreadsheet;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests extends TestBase {
    private static final By homeSortBtn = AppiumBy.accessibilityId("sort button");
    private static final By userNameIsRequiredMessage = AppiumBy.accessibilityId("Username-error-message");
    private static final By passwordIsRequiredMessage = AppiumBy.accessibilityId("Password-error-message");
    private static final By invalidCredentialsMessage = AppiumBy.accessibilityId(("generic-error-message"));


    @Test(priority = 1)
    public void loginWithEmptyUserName() throws IOException {
        new HomeScreen(driver);
        new LoginScreen(driver);
        HomeScreen.homeScreenActions();
        LoginScreen.loginActions("",
                Spreadsheet.getData(System.getProperty("user.dir") + "/src/resources/TestData/appiumLoginData.xlsx", "validData", 1, 1));
        Assert.assertTrue(driver.findElement(userNameIsRequiredMessage).isDisplayed());
    }

    @Test(priority = 2)
    public void loginWithEmptyPassword() throws IOException {
        new HomeScreen(driver);
        new LoginScreen(driver);
        HomeScreen.homeScreenActions();
        LoginScreen.loginActions(
                Spreadsheet.getData(System.getProperty("user.dir") + "/src/resources/TestData/appiumLoginData.xlsx", "validData", 1, 0),
                "");
        Assert.assertTrue(driver.findElement(passwordIsRequiredMessage).isDisplayed());
    }

    @Test(priority = 3)
    public void loginWithInvalidUserName() throws IOException {
        new HomeScreen(driver);
        new LoginScreen(driver);
        HomeScreen.homeScreenActions();
        LoginScreen.loginActions(
                Spreadsheet.getData(System.getProperty("user.dir") + "/src/resources/TestData/appiumLoginData.xlsx", "invalidData", 1, 0),
                Spreadsheet.getData(System.getProperty("user.dir") + "/src/resources/TestData/appiumLoginData.xlsx", "validData", 1, 1));
        Assert.assertTrue(driver.findElement(invalidCredentialsMessage).isDisplayed());
    }

    @Test(priority = 4)
    public void loginWithInvalidPassword() throws IOException {
        new HomeScreen(driver);
        new LoginScreen(driver);
        HomeScreen.homeScreenActions();
        LoginScreen.loginActions(
                Spreadsheet.getData(System.getProperty("user.dir") + "/src/resources/TestData/appiumLoginData.xlsx", "validData", 1, 0),
                Spreadsheet.getData(System.getProperty("user.dir") + "/src/resources/TestData/appiumLoginData.xlsx", "invalidData", 1, 1));
        Assert.assertTrue(driver.findElement(invalidCredentialsMessage).isDisplayed());
    }

    @Test(priority = 5)
    public void loginWithValidCredentials() throws IOException {
        new HomeScreen(driver);
        new LoginScreen(driver);
        HomeScreen.homeScreenActions();
        LoginScreen.loginActions(
                Spreadsheet.getData(System.getProperty("user.dir") + "/src/resources/TestData/appiumLoginData.xlsx", "validData", 1, 0),
                Spreadsheet.getData(System.getProperty("user.dir") + "/src/resources/TestData/appiumLoginData.xlsx", "validData", 1, 1));
        Assert.assertTrue(driver.findElement(homeSortBtn).isDisplayed());
    }
}
