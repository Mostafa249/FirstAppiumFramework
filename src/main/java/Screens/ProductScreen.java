package Screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductScreen {
    private static AppiumDriver driver;
    private static final By plusBtn = AppiumBy.xpath("//*[@content-desc='counter plus button']");
    private static final By minusBtn = AppiumBy.xpath("//*[@content-desc='counter minus button']");
    private static final By addToCartBtn = AppiumBy.accessibilityId("Add To Cart button");
    private static final By cartBadge = AppiumBy.xpath("//*[@content-desc='cart badge']");

    public ProductScreen(AppiumDriver driver) {
        ProductScreen.driver = driver;
    }

    @Step("Click on open plus button .")
    public static void clickOnPlusBtn() {
        driver.findElement(plusBtn).click();
    }

    @Step("Click on open minus button .")
    public static void clickOnMinusBtn() {
        driver.findElement(minusBtn).click();
    }

    @Step("Click on add to cart button .")
    public static void clickOnAddToCartBtn() {
        driver.findElement(addToCartBtn).click();
    }

    @Step("Change product color .")
    public static void changeProductColor(String color) {
        driver.findElement(AppiumBy.xpath("//*[@content-desc='" + color + " circle']")).click();
    }

    @Step("Click on cart badge button .")
    public static void clickOnCartBadgeBtn() {
        driver.findElement(cartBadge).click();
    }
}
