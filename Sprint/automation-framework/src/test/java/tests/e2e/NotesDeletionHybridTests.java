package tests.e2e;

import api.NotesApi;
import base.BaseTest;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.NotesPage;

public class NotesDeletionHybridTests
        extends BaseTest {

    LoginPage loginPage;

    NotesPage notesPage;

    // Unique title every run

    String title =
        "DeleteNote_" + (int)(Math.random()*1000);

    String description =
            "Delete validation";

    // TC-16

    @Test(priority = 1)

    public void verifyDeletedNoteDisappearsFromUi()
            throws InterruptedException {

        loginPage =
                new LoginPage(driver);

        notesPage =
                new NotesPage(driver);

        // Login

        loginPage.login(
                "testuser1+1@gmail.com",
                "user1A@x"
        );

        // Create note

        notesPage.createNote(
                title,
                description
        );

        // Get latest note id

        String noteId =
                NotesApi.getLatestNoteId();

        // Delete note through API

        Response response =
                NotesApi.deleteNote(noteId);

        // Verify delete success

        Assert.assertEquals(
                response.getStatusCode(),
                200
        );

        // Wait

        Thread.sleep(3000);

        // Refresh UI

        driver.navigate().refresh();

        Thread.sleep(2000);

        // Verify deleted note not visible

        Assert.assertFalse(
                driver.getPageSource()
                        .contains(title),

                "Deleted note still visible in UI"
        );
    }

    // TC-17

    @Test(
            priority = 2,
            dependsOnMethods =
                    "verifyDeletedNoteDisappearsFromUi"
    )

    public void verifyDeletedNoteDoesNotReappearAfterRefresh()
            throws InterruptedException {

        loginPage =
                new LoginPage(driver);

        notesPage =
                new NotesPage(driver);

        // Login again

        loginPage.login(
                "testuser1+1@gmail.com",
                "user1A@x"
        );

        // Refresh again

        driver.navigate().refresh();

        Thread.sleep(2000);

        // Verify note not visible

        Assert.assertFalse(
                driver.getPageSource()
                        .contains(title),

                "Deleted note reappeared after refresh"
        );
    }
}