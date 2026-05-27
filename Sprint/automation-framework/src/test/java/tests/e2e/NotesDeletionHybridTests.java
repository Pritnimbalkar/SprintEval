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

    String title =
            "Delete Hybrid Note";

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

        // Fetch latest note ID

        String noteId =
                NotesApi.getLatestNoteId();

        // Delete note through API

        Response response =
                NotesApi.deleteNote(noteId);

        Assert.assertEquals(
                response.getStatusCode(),
                200
        );

        // Backend sync wait

        Thread.sleep(3000);

        // Retry refresh logic

        boolean notePresent = true;

        // for (int i = 1; i <= 3; i++) {

        driver.navigate().refresh();

        Thread.sleep(2000);

            notePresent =
                    driver.getPageSource()
                            .contains(title);

        //     if (!notePresent) {

        //         break;
        //     }
        // }

        Assert.assertFalse(
                notePresent,
                "Deleted note still visible in UI"
        );
    }

    // TC-17

    @Test(
            priority = 2,
            dependsOnMethods =
                    "verifyDeletedNoteDisappearsFromUi"
    )

    public void verifyDeletedNoteDoesNotReappearAfterRefresh() {

        loginPage =
                new LoginPage(driver);

        notesPage =
                new NotesPage(driver);

        // Login again

        loginPage.login(
                "testuser1+1@gmail.com",
                "user1A@x"
        );

        // Refresh

        driver.navigate().refresh();

        boolean notePresent =
                driver.getPageSource()
                        .contains(title);

        Assert.assertFalse(
                notePresent,
                "Deleted note reappeared after refresh"
        );
    }
}