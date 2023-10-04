package Tests;

import Screens.*;
import Utiliti.Logs;
import Utiliti.Spreadsheet;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

public class CheckoutTest extends TestBase {
    private static final By fullNameValidationMessage = AppiumBy.accessibilityId("Full Name*-error-message");
    private static final By addressLine1ValidationMessage = AppiumBy.accessibilityId("Address Line 1*-error-message");
    private static final By cityValidationMessage = AppiumBy.accessibilityId("City*-error-message");
    private static final By zipCodeValidationMessage = AppiumBy.accessibilityId("Zip Code*-error-message");
    private static final By countryValidationMessage = AppiumBy.accessibilityId("Country*-error-message");
    private static final By checkoutPaymentScreenElement = AppiumBy.accessibilityId("checkout payment screen");

    @BeforeMethod()
    public void AndroidDriver() throws IOException {
        Logs.info("Set Android driver");
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), option);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        Logs.info("App  is opened");
        new HomeScreen(driver);
        new LoginScreen(driver);
        new ProductScreen(driver);
        new CartScreen(driver);
        new CheckoutScreen(driver);

        Logs.info("Login successfully before adding item to cart ");
        HomeScreen.loginActions();
        LoginScreen.loginActions(
                Spreadsheet.getData("appiumLoginData.xlsx", "validData", 1, 0),
                Spreadsheet.getData("appiumLoginData.xlsx", "validData", 1, 1));
        Logs.info("Add a product to cart");
        HomeScreen.addProductActions("1");
        Logs.info("Change the product color to be red");
        ProductScreen.changeProductColor("red");
        Logs.info("Click on add to cart button ");
        ProductScreen.clickOnAddToCartBtn();
        Logs.info("Click in cart badge");
        ProductScreen.clickOnCartBadgeBtn();
        Logs.info("Click on Proceed checkout button ");
        CartScreen.clickOnProceedCheckoutBtn();
    }

    @Test(priority = 1, description = "Checkout without full name")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Check without full name  ")
    public void checkoutWithoutFullName() throws IOException {
        Logs.info("Enter all checkout mandatory fields except full name");
        CheckoutScreen.checkoutActions("",
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 1),
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 2),
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 3),
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 4)
        );
        Logs.info("Assert on full name validation message");
        Assert.assertTrue(driver.findElement(fullNameValidationMessage).isDisplayed());
    }

    @Test(priority = 2, description = "Checkout without address line 1 ")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Check without address line 1  ")
    public void checkoutWithoutAddressLine1() throws IOException {
        Logs.info("Enter all checkout mandatory fields except full name");
        CheckoutScreen.checkoutActions(Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 0),
                "",
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 2),
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 3),
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 4)
        );
        Logs.info("Assert on full name validation message");
        Assert.assertTrue(driver.findElement(addressLine1ValidationMessage).isDisplayed());
    }

    @Test(priority = 3, description = "Checkout without city ")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Check without city  ")
    public void checkoutWithoutCity() throws IOException {
        Logs.info("Enter all checkout mandatory fields except full name");
        CheckoutScreen.checkoutActions(Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 0),
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 1),
                "",
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 3),
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 4)
        );
        Logs.info("Assert on full name validation message");
        Assert.assertTrue(driver.findElement(cityValidationMessage).isDisplayed());
    }

    @Test(priority = 4, description = "Checkout without zip code ")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Check without zip code  ")
    public void checkoutWithoutZipCode() throws IOException {
        Logs.info("Enter all checkout mandatory fields except full name");
        CheckoutScreen.checkoutActions(Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 0),
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 1),
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 2),
                "",
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 4)
        );
        Logs.info("Assert on full name validation message");
        Assert.assertTrue(driver.findElement(zipCodeValidationMessage).isDisplayed());
    }

    @Test(priority = 5, description = "Checkout without country ")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Check without country  ")
    public void checkoutWithoutCountry() throws IOException {
        Logs.info("Enter all checkout mandatory fields except full name");
        CheckoutScreen.checkoutActions(Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 0),
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 1),
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 2),
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 3),
                ""
        );
        Logs.info("Assert on full name validation message");
        Assert.assertTrue(driver.findElement(countryValidationMessage).isDisplayed());
    }

    @Test(priority = 6, description = "Checkout successfully ")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Check successfully with mandatory data only  ")
    public void checkoutWithCorrectData() throws IOException {
        Logs.info("Enter all checkout mandatory fields except full name");
        CheckoutScreen.checkoutActions(Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 0),
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 1),
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 2),
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 3),
                Spreadsheet.getData("CheckoutData.xlsx", "sheet1", 1, 4)

        );
        Logs.info("Assert on full name validation message");
        Assert.assertTrue(driver.findElement(checkoutPaymentScreenElement).isDisplayed());
    }
}