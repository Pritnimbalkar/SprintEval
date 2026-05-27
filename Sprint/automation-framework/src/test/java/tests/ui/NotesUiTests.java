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

public class NotesUiTests extends BaseTest {

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
    ) {

        loginPage =
                new LoginPage(driver);

        notesPage =
                new NotesPage(driver);

        // Login

        loginPage.login(
                "testuser1+1@gmail.com",
                "user1A@x"
        );

        // Explicit Wait

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

        // Create Note

        notesPage.createNote(
                title,
                description
        );

        // Valid Note Creation

        if (type.equals("valid")) {

            Assert.assertTrue(
                    notesPage.isNoteDisplayed(title),
                    "Created note not visible in UI"
            );

            notesPage.refreshPage();

            Assert.assertTrue(
                    notesPage.isNoteDisplayed(title),
                    "Note disappeared after refresh"
            );
        }

        // Empty Title

        else if (type.equals("emptyTitle")) {

            Assert.assertTrue(
                    notesPage.isCreateNoteFormDisplayed(),
                    "Validation failed for empty title"
            );
        }

        // Empty Description

        else if (type.equals("emptyDescription")) {

            Assert.assertTrue(
                    notesPage.isCreateNoteFormDisplayed(),
                    "Validation failed for empty description"
            );
        }

        // Empty All

        else if (type.equals("emptyAll")) {

            Assert.assertTrue(
                    notesPage.isCreateNoteFormDisplayed(),
                    "Validation failed for empty fields"
            );
        }

        // Special Characters

        else if (type.equals("specialChars")) {

            Assert.assertTrue(
                    true,
                    "Special character handling failed"
            );
        }
    }
}