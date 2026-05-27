package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NotesPage {

    WebDriver driver;

    public NotesPage(WebDriver driver) {

        this.driver = driver;
    }

    // Locators

    // Locators

    private By addNoteButton =
        By.cssSelector("[data-testid='add-new-note']");

    private By titleInput =
        By.id("title");

    private By descriptionInput =
        By.id("description");

    private By createButton =
        By.cssSelector("[data-testid='note-submit']");

    private By notesList =
        By.cssSelector("[data-testid='note-card']");

    

    //Actions

    public void clickAddNote() {

    By freshAddNoteButton =
            By.cssSelector(
                "[data-testid='add-new-note']"
            );

    WebElement addBtn =
            driver.findElement(
                    freshAddNoteButton
            );

    JavascriptExecutor js =
            (JavascriptExecutor) driver;

    js.executeScript(
            "arguments[0].scrollIntoView(true);",
            addBtn
    );

    js.executeScript(
            "arguments[0].click();",
            addBtn
    );
    }

    public void enterTitle(String title) {

        driver.findElement(titleInput)
                .sendKeys(title);
    }

    public void enterDescription(String description) {

        driver.findElement(descriptionInput)
                .sendKeys(description);
    }

    public void clickCreate() {

    JavascriptExecutor js =
            (JavascriptExecutor) driver;

    js.executeScript(
            "arguments[0].click();",
            driver.findElement(createButton)
    );
    }

    public boolean isCreateNoteFormDisplayed() {

    return driver.findElement(createButton)
            .isDisplayed();
    }

    public void createNote(
        String title,
        String description
    ) 
    {

    clickAddNote();

    WebDriverWait wait =
            new WebDriverWait(
                    driver,
                    Duration.ofSeconds(10)
            );

    wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    titleInput
            )
    );

    driver.findElement(titleInput)
            .sendKeys(title);

    driver.findElement(descriptionInput)
            .sendKeys(description);

    clickCreate();
    }

    public boolean isNoteDisplayed(
        String noteTitle
    ) 
   {

    return driver.getPageSource()
            .contains(noteTitle);
    }

    public void refreshPage() {

    driver.navigate().refresh();
    }
}