package Tests;

import Screens.HomeScreen;
import Screens.LoginScreen;
import Utiliti.Logs;
import Utiliti.Spreadsheet;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
@Listeners(TestUtiliti.Listeners.class)
public class LoginTests extends TestBase {
    private static final By homeSortBtn = AppiumBy.accessibilityId("sort button");
    private static final By userNameIsRequiredMessage = AppiumBy.accessibilityId("Username-error-message");
    private static final By passwordIsRequiredMessage = AppiumBy.accessibilityId("Password-error-message");
    private static final By invalidCredentialsMessage = AppiumBy.accessibilityId(("generic-error-message"));


    @Test(priority = 1, description = "invalid login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Un successful login with empty user name field")
    public void loginWithEmptyUserName() throws IOException {
        HomeScreen.loginActions();
        LoginScreen.loginActions("",
                Spreadsheet.getData(System.getProperty("user.dir") + "/src/resources/TestData/appiumLoginData.xlsx", "validData", 1, 1));
        Logs.info("Assert on empty user name error message");
        Assert.assertTrue(driver.findElement(userNameIsRequiredMessage).isDisplayed());
    }

    @Test(priority = 2, description = "invalid login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Un successful login with empty password field")
    public void loginWithEmptyPassword() throws IOException {
        HomeScreen.loginActions();
        LoginScreen.loginActions(
                Spreadsheet.getData(System.getProperty("user.dir") + "/src/resources/TestData/appiumLoginData.xlsx", "validData", 1, 0),
                "");
        Logs.info("Assert on empty password error message");
        Assert.assertTrue(driver.findElement(passwordIsRequiredMessage).isDisplayed());
    }

    @Test(priority = 3, description = "invalid login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Un successful login with invalid user name field")
    public void loginWithInvalidUserName() throws IOException {
        HomeScreen.loginActions();
        LoginScreen.loginActions(
                Spreadsheet.getData(System.getProperty("user.dir") + "/src/resources/TestData/appiumLoginData.xlsx", "invalidData", 1, 0),
                Spreadsheet.getData(System.getProperty("user.dir") + "/src/resources/TestData/appiumLoginData.xlsx", "validData", 1, 1));
        Logs.info("Assert on wrong credentials error message");
        Assert.assertTrue(driver.findElement(invalidCredentialsMessage).isDisplayed());
    }

    @Test(priority = 4, description = "invalid login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Un successful login with invalid password field")
    public void loginWithInvalidPassword() throws IOException {
        HomeScreen.loginActions();
        LoginScreen.loginActions(
                Spreadsheet.getData(System.getProperty("user.dir") + "/src/resources/TestData/appiumLoginData.xlsx", "validData", 1, 0),
                Spreadsheet.getData(System.getProperty("user.dir") + "/src/resources/TestData/appiumLoginData.xlsx", "invalidData", 1, 1));
        Logs.info("Assert on wrong credentials error message");
        Assert.assertTrue(driver.findElement(invalidCredentialsMessage).isDisplayed());
    }

    @Test(priority = 5, description = "Valid login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Successful login with correct data")
    public void loginWithValidCredentials() throws IOException {
        HomeScreen.loginActions();
        LoginScreen.loginActions(
                Spreadsheet.getData(System.getProperty("user.dir") + "/src/resources/TestData/appiumLoginData.xlsx", "validData", 1, 0),
                Spreadsheet.getData(System.getProperty("user.dir") + "/src/resources/TestData/appiumLoginData.xlsx", "validData", 1, 1));
        Logs.info("Assert on login successfully ");
        Assert.assertTrue(driver.findElement(homeSortBtn).isDisplayed());
    }
}
