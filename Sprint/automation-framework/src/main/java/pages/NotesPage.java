package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.JavascriptExecutor;

public class NotesPage {

    WebDriver driver;

    public NotesPage(WebDriver driver) {

        this.driver = driver;
    }

    // Locators

    private By addNoteButton =
            By.cssSelector(
                "[data-testid='add-new-note']"
            );

    private By titleInput =
            By.id("title");

    private By descriptionInput =
            By.id("description");

    private By createButton =
            By.cssSelector(
                "[data-testid='note-submit']"
            );

    // Wait utility

  public WebDriverWait getWait() {

    return new WebDriverWait(
            driver,
            Duration.ofSeconds(10)
    );
}

    // Click Add Note

public void clickAddNote() {

    getWait().until(
            ExpectedConditions
                    .visibilityOfElementLocated(
                            addNoteButton
                    )
    );

    WebElement button =
            driver.findElement(
                    addNoteButton
            );

    JavascriptExecutor js =
            (JavascriptExecutor) driver;

    js.executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            button
    );

    js.executeScript(
            "arguments[0].click();",
            button
    );
}

    // Enter Title

    public void enterTitle(String title) {

        getWait().until(
                ExpectedConditions
                        .visibilityOfElementLocated(
                                titleInput
                        )
        );

        driver.findElement(titleInput)
                .clear();

        driver.findElement(titleInput)
                .sendKeys(title);
    }

    // Enter Description

    public void enterDescription(
            String description
    ) {

        driver.findElement(descriptionInput)
                .clear();

        driver.findElement(descriptionInput)
                .sendKeys(description);
    }

    // Click Create

    public void clickCreate() {

    WebElement createBtn =
            driver.findElement(
                    createButton
            );

    JavascriptExecutor js =
            (JavascriptExecutor) driver;

    js.executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            createBtn
    );

    js.executeScript(
            "arguments[0].click();",
            createBtn
    );
        }

    // Form Display Validation

    public boolean isCreateNoteFormDisplayed() {

        return getWait().until(
                ExpectedConditions
                        .visibilityOfElementLocated(
                                createButton
                        )
        ).isDisplayed();
    }

    // Create Note Flow

    public void createNote(
            String title,
            String description
    ) {

        clickAddNote();

        enterTitle(title);

        enterDescription(description);

        clickCreate();
    }

    // Verify Note Exists

    public boolean isNoteDisplayed(
            String noteTitle
    ) {

        return driver.findElements(
                By.xpath(
                    "//*[contains(text(),'"
                    + noteTitle +
                    "')]"
                )
        ).size() > 0;
    }

    // Refresh Page

    public void refreshPage() {

        driver.navigate().refresh();

        getWait().until(
                ExpectedConditions
                        .visibilityOfElementLocated(
                                addNoteButton
                        )
        );
    }
}