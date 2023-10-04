package Tests;

import Screens.CartScreen;
import Screens.HomeScreen;
import Screens.LoginScreen;
import Screens.ProductScreen;
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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

@Listeners(TestUtiliti.Listeners.class)
public class AddToCartTests extends TestBase {
    private static final By redCircle = AppiumBy.xpath("//*[@content-desc='red circle']");
    private static final By totalItemsPriceElement = AppiumBy.xpath("//*[@content-desc=\"total price\"]");
    private static final By totalItemsCountElement = AppiumBy.xpath("//*[@content-desc=\"total number\"]");

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

        Logs.info("Login successfully before adding item to cart ");
        HomeScreen.loginActions();
        LoginScreen.loginActions(
                Spreadsheet.getData("appiumLoginData.xlsx", "validData", 1, 0),
                Spreadsheet.getData("appiumLoginData.xlsx", "validData", 1, 1));
    }

    @Test(priority = 1, description = "Change item color")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Change the color for an item before adding it to cart ")
    public void ChangeTheItemColorBeforeAddingItToCart() {
        Logs.info("Add a product to cart");
        HomeScreen.addProductActions("1");
        Logs.info("Change the product color to be red");
        ProductScreen.changeProductColor("red");
        Logs.info("Click on add to cart button ");
        ProductScreen.clickOnAddToCartBtn();
        Logs.info("Click in cart badge");
        ProductScreen.clickOnCartBadgeBtn();
        Logs.info("Assert that product added with red color");
        Assert.assertTrue(driver.findElement(redCircle).isDisplayed());

    }

    @Test(priority = 2, description = "Add items to cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Check existing of two added items to cart ")
    public void addTwoItemsToCart() {
        Logs.info("Add first product to cart");
        HomeScreen.addProductActions("1");
        Logs.info("Click on add to cart button to add first product ");
        ProductScreen.clickOnAddToCartBtn();
        Logs.info("Back to home screen ");
        driver.navigate().back();
        Logs.info("Add second product to cart");
        HomeScreen.addProductActions("6");
        Logs.info("Click on add to cart button to add second product");
        ProductScreen.clickOnAddToCartBtn();
        Logs.info("Click in cart badge");
        ProductScreen.clickOnCartBadgeBtn();
        Logs.info("Assert on number of items were added to cart ");
        Assert.assertEquals(driver.findElements(AppiumBy.xpath("//*[@content-desc=\"product row\"]")).size(), 2);
    }

    @Test(priority = 3, description = "Validate total price ")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validate total price of two items added to cart ")
    public void calculateTotalPriceOfAddedItems() {
        Logs.info("Add first product to cart");
        HomeScreen.addProductActions("1");
        Logs.info("Click on add to cart button to add first product ");
        ProductScreen.clickOnAddToCartBtn();
        Logs.info("Back to home screen ");
        driver.navigate().back();
        Logs.info("Add second product to cart");
        HomeScreen.addProductActions("6");
        Logs.info("Click on add to cart button to add second product");
        ProductScreen.clickOnAddToCartBtn();
        Logs.info("Click in cart badge");
        ProductScreen.clickOnCartBadgeBtn();
        Logs.info("Assert on total price of added items ");
        String totalPrice = driver.findElement(totalItemsPriceElement).getText();
        Assert.assertEquals(totalPrice, "$45.98");
    }

    @Test(priority = 4, description = "Validate total price and total items count ")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validate total price and total items Count after removing an item from cart ")
    public void checkTotalPriceAndTotalItemsAfterRemovingAnItemFromCart() {
        Logs.info("Add first product to cart");
        HomeScreen.addProductActions("1");
        Logs.info("Click on add to cart button to add first product ");
        ProductScreen.clickOnAddToCartBtn();
        Logs.info("Back to home screen ");
        driver.navigate().back();
        Logs.info("Add second product to cart");
        HomeScreen.addProductActions("6");
        Logs.info("Click on add to cart button to add second product");
        ProductScreen.clickOnAddToCartBtn();
        Logs.info("Click in cart badge");
        ProductScreen.clickOnCartBadgeBtn();
        Logs.info("Remove first product from cart ");
        CartScreen.removeProductActions("1");
        Logs.info("Assert on total price of remaining item ");
        String totalPrice = driver.findElement(totalItemsPriceElement).getText();
        Assert.assertEquals(totalPrice, "$15.99");
        Logs.info("Assert on total items count of remaining item ");
        String totalItems = driver.findElement(totalItemsCountElement).getText();
        Assert.assertEquals(totalItems, "1 item");

    }

}
