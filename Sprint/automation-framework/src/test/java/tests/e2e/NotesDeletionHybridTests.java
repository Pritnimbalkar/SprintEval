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
            "DeleteNote_"
            + System.currentTimeMillis();

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

        // Wait for backend sync

        Thread.sleep(3000);

        // Verify note created

        Assert.assertTrue(
                notesPage.isNoteDisplayed(title),
                "Created note not visible before deletion"
        );

        // Fetch latest note ID

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

        // Wait after delete

        Thread.sleep(3000);

        // Refresh page
        driver.navigate().refresh();

        Thread.sleep(5000);
        notesPage.refreshPage();

        // Wait after refresh

        Thread.sleep(3000);

        // Verify deleted note not visible

        Assert.assertFalse(
                notesPage.isNoteDisplayed(title),
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

        notesPage.refreshPage();

        // Wait after refresh

        Thread.sleep(3000);

        // Verify note still absent

        Assert.assertFalse(
                notesPage.isNoteDisplayed(title),
                "Deleted note reappeared after refresh"
        );
    }
}