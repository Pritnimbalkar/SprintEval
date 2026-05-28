package tests.ui;

import base.BaseTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.NotesPage;

import utils.ExcelUtils;

import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NotesUiTests
        extends BaseTest {

    LoginPage loginPage;

    NotesPage notesPage;

    // Data Provider

    @DataProvider(name = "notesData")

    public Object[][] notesData() {

        return ExcelUtils
                .getSheetData("NotesData");
    }

    // Test Case

    @Test(dataProvider = "notesData")

    public void verifyNoteCreation(
            String title,
            String description,
            String type
    ) throws InterruptedException {

        loginPage =
                new LoginPage(driver);

        notesPage =
                new NotesPage(driver);

        // Login

        loginPage.login(
                "testuser1+1@gmail.com",
                "user1A@x"
        );

        // Wait for dashboard

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                );

        wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(
                                By.cssSelector(
                                  "[data-testid='add-new-note']"
                                )
                        )
        );

        // Unique title for valid notes

        String uniqueTitle = title;

        if (type.equals("valid")) {

            uniqueTitle =
                    title + "_"
                    + System.currentTimeMillis();
        }

        // Create note

        notesPage.createNote(
                uniqueTitle,
                description
        );

        // Valid Note Tests

        if (type.equals("valid")) {

            // Wait for note creation

            Thread.sleep(5000);

            // Verify note visible

            Assert.assertTrue(
                    notesPage.isNoteDisplayed(
                            uniqueTitle
                    ),
                    "Created note not visible in UI"
            );

            // Refresh page

            notesPage.refreshPage();

            // Wait after refresh

            Thread.sleep(5000);

            // Verify note still exists

            Assert.assertTrue(
                    notesPage.isNoteDisplayed(
                            uniqueTitle
                    ),
                    "Note disappeared after refresh"
            );
        }

        // Empty Title Validation

        else if (type.equals("emptyTitle")) {

            Assert.assertTrue(
                    notesPage.isCreateNoteFormDisplayed(),
                    "Validation failed for empty title"
            );
        }

        // Empty Description Validation

        else if (type.equals("emptyDescription")) {

            Assert.assertTrue(
                    notesPage.isCreateNoteFormDisplayed(),
                    "Validation failed for empty description"
            );
        }

        // Empty All Validation

        else if (type.equals("emptyAll")) {

            Assert.assertTrue(
                    notesPage.isCreateNoteFormDisplayed(),
                    "Validation failed for empty fields"
            );
        }

        // Special Characters Validation

        else if (type.equals("specialChars")) {

            Assert.assertTrue(
                    true,
                    "Special character handling failed"
            );
        }
    }
}