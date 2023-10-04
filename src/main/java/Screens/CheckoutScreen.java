package Screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CheckoutScreen {
    private static AppiumDriver driver;
    private static final By fullNameField = AppiumBy.accessibilityId("Full Name* input field");
    private static final By addressLine1Field = AppiumBy.accessibilityId("Address Line 1* input field");
    private static final By cityField = AppiumBy.accessibilityId("City* input field");
    private static final By zipCodeField = AppiumBy.accessibilityId("Zip Code* input field");
    private static final By countryField = AppiumBy.accessibilityId("Country* input field");
    private static final By toPaymentButton = AppiumBy.accessibilityId("To Payment button");

    public CheckoutScreen(AppiumDriver driver) {
        CheckoutScreen.driver = driver;
    }

    @Step("Checkout with data :full name: {fullName},address line 1: {addressLine1} , city: {city} , zip code: {zipCode} ,country: {country} ")
    public static void checkoutActions(String fullName, String addressLine1, String city, String zipCode, String country) {
        driver.findElement(fullNameField).sendKeys(fullName);
        driver.findElement(addressLine1Field).sendKeys(addressLine1);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(zipCodeField).sendKeys(zipCode);
        driver.findElement(countryField).sendKeys(country);
        driver.findElement(toPaymentButton).click();

    }
}
