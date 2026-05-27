package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By emailInput = By.id("email");

    private By passwordInput = By.id("password");

    private By loginButton =
            By.cssSelector("[data-testid='login-submit']");

    // Success locator
    private By addNoteButton =
            By.xpath("//button[contains(text(),'Add Note')]");

    // Actions
    public void enterEmail(String email) {

        driver.findElement(emailInput)
                .sendKeys(email);
    }

    public void enterPassword(String password) {

        driver.findElement(passwordInput)
                .sendKeys(password);
    }

    public void clickLogin() {

        WebElement loginBtn =
                driver.findElement(loginButton);

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView(true);",
                loginBtn
        );

        js.executeScript(
                "arguments[0].click();",
                loginBtn
        );
    }

    public void login(String email, String password) {

        enterEmail(email);

        enterPassword(password);

        clickLogin();
    }

    public boolean isDashboardDisplayed() {

    try {

        WebDriverWait wait =
                new WebDriverWait(driver,
                        Duration.ofSeconds(10));

        WebElement element =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        addNoteButton
                                )
                );

        return element.isDisplayed();

    } catch (Exception e) {

        return false;
    }
}
}