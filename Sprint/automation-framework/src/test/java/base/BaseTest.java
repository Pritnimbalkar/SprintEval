package base;

import drivers.DriverFactory;
import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.ITestResult;
import utils.ScreenshotUtils;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        DriverFactory.initDriver();

        driver = DriverManager.getDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://practice.expandtesting.com/notes/app/login");
    }

    @AfterMethod
    

public void tearDown(
        ITestResult result
) {

    if (result.getStatus()
            == ITestResult.FAILURE) {

        ScreenshotUtils
                .captureScreenshot(driver);
    }

    DriverManager.quitDriver();
}
}