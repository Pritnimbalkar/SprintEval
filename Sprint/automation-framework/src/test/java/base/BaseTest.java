package base;

import drivers.DriverFactory;
import drivers.DriverManager;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)

    public void setUp() {

        // Initialize browser

        DriverFactory.initDriver();

        driver =
                DriverManager.getDriver();

        // Browser timeouts

        driver.manage()
                .timeouts()
                .implicitlyWait(
                        Duration.ofSeconds(10)
                );

        driver.manage()
                .timeouts()
                .pageLoadTimeout(
                        Duration.ofSeconds(60)
                );

        // Open application

        driver.get(
            "https://practice.expandtesting.com/notes/app/login"
        );
    }

    // Used by listeners

    public static WebDriver getDriver() {

        return DriverManager.getDriver();
    }

    @AfterMethod(alwaysRun = true)

    public void tearDown() {

        try {

            WebDriver currentDriver =
                    DriverManager.getDriver();

            if (currentDriver != null) {

                currentDriver.quit();

                DriverManager.quitDriver();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}