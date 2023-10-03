package TestUtiliti;

import Screens.HomeScreen;
import Utiliti.Logs;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
    AppiumDriver driver;

    private static String getTestMethodNAME(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Web page screenshot .", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {

        Logs.info(getTestMethodNAME(iTestResult) + " is starting.");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        new HomeScreen(driver);
        System.out.println("I am on test passed method" + getTestMethodNAME(iTestResult) + "passed");
        Object testClass = iTestResult.getInstance();
        if (driver instanceof WebDriver) {
            System.out.println("Screenshot captured for test case " + getTestMethodNAME(iTestResult));
            saveScreenshotPNG(driver);
        }
        saveTextLog(getTestMethodNAME(iTestResult) + "Passed and screen shot has been taken");

    }

    @Override
    public void onTestFailure(ITestResult result) {

    }

    @Override
    public void onTestSkipped(ITestResult iTestResultresult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        Logs.info("Finishing " + context.getName());
    }
}
