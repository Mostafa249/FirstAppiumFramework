package Screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartScreen {
    private static AppiumDriver driver;
    private static final By proceedToCheckoutBtn = AppiumBy.accessibilityId("Proceed To Checkout button");

    public CartScreen(AppiumDriver driver) {
        CartScreen.driver = driver;
    }

    @Step("Remove product from cart . ")
    public static void removeProductActions(String productIndex) {
        By product = AppiumBy.xpath("(//*[@content-desc=\"remove item\"])[" + productIndex + "]");
        driver.findElement(product).click();
    }

    @Step("Click on proceed to checkout button. ")
    public static void clickOnProceedCheckoutBtn() {
        driver.findElement(proceedToCheckoutBtn).click();
    }

}
