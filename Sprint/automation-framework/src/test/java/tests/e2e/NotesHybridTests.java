package tests.e2e;

import api.NotesApi;

import base.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.NotesPage;

public class NotesHybridTests
        extends BaseTest {

    LoginPage loginPage;

    NotesPage notesPage;

    // Unique title every execution

    String title =
            "Hybrid_Note_"
            + System.currentTimeMillis();

    String description =
            "Created from UI";

    String apiResponse;

    // TC-12

    @Test(priority = 1)

    public void verifyUiCreatedNoteInApi()
            throws InterruptedException {

        loginPage =
                new LoginPage(driver);

        notesPage =
                new NotesPage(driver);

        // Login from UI

        loginPage.login(
                "testuser1+1@gmail.com",
                "user1A@x"
        );

        // Create note from UI

        notesPage.createNote(
                title,
                description
        );

        // Wait for backend sync

        Thread.sleep(3000);

        // Fetch notes from API

        apiResponse =
                NotesApi.getNotesResponse();

        System.out.println(apiResponse);

        // Verify note title exists

        Assert.assertTrue(
                apiResponse.contains(title),
                "UI-created note missing in API response"
        );

        // Verify description exists

        Assert.assertTrue(
                apiResponse.contains(description),
                "Description mismatch in API response"
        );

        // Verify note ID exists

        Assert.assertTrue(
                apiResponse.contains("_id"),
                "Note ID missing in API response"
        );
    }

    // TC-13

    @Test(
            priority = 2,
            dependsOnMethods =
                    "verifyUiCreatedNoteInApi"
    )

    public void verifyCreatedNoteStillExistsInApi()
            throws InterruptedException {

        // Wait before API validation

        Thread.sleep(2000);

        // Fetch notes again

        apiResponse =
                NotesApi.getNotesResponse();

        // Verify same note still exists

        Assert.assertTrue(
                apiResponse.contains(title),
                "Created note missing after second API fetch"
        );

        // Verify description again

        Assert.assertTrue(
                apiResponse.contains(description),
                "Description mismatch after second fetch"
        );
    }
}