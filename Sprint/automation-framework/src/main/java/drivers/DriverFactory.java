package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static void initDriver() {

        // Setup ChromeDriver

        WebDriverManager.chromedriver().setup();

        // Chrome options

        ChromeOptions options =
                new ChromeOptions();

        // Disable interruptions

        options.addArguments(
                "--disable-notifications"
        );

        options.addArguments(
                "--disable-popup-blocking"
        );

        options.addArguments(
                "--disable-infobars"
        );

        // Stability fixes

        options.addArguments(
                "--remote-allow-origins=*"
        );

        options.addArguments(
                "--disable-dev-shm-usage"
        );

        options.addArguments(
                "--no-sandbox"
        );

        options.addArguments(
                "--disable-gpu"
        );

        // Start browser maximized

        options.addArguments(
                "--start-maximized"
        );

        // Create driver

        WebDriver driver =
                new ChromeDriver(options);

        // Maximize window

        driver.manage()
                .window()
                .maximize();

        // Store driver

        DriverManager.setDriver(driver);
    }
}