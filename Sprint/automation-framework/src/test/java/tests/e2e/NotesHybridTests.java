package tests.e2e;

import api.NotesApi;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.NotesPage;

public class NotesHybridTests extends BaseTest {

    LoginPage loginPage;

    NotesPage notesPage;

    String title =
            "Hybrid Note";

    String description =
            "Created from UI";

    String apiResponse;

    @Test(priority = 1)

    public void verifyUiCreatedNoteInApi() {

        loginPage =
                new LoginPage(driver);

        notesPage =
                new NotesPage(driver);

        // Login from UI

        loginPage.login(
                "testuser1+1@gmail.com",
                "user1A@x"
        );

        // Create Note from UI

        notesPage.createNote(
                title,
                description
        );

        // Fetch notes from API

        apiResponse =
                NotesApi.getNotesResponse();

        System.out.println(apiResponse);

        // TC-12

        Assert.assertTrue(
                apiResponse.contains(title),
                "UI-created note missing in API response"
        );
    }

    @Test(priority = 2)

    public void verifyFieldMatchingAndNoteIdConsistency() {

        // Fetch notes again

        apiResponse =
                NotesApi.getNotesResponse();

        // Description validation

        Assert.assertTrue(
                apiResponse.contains(description),
                "Description mismatch in API response"
        );

        // Note ID validation

        Assert.assertTrue(
                apiResponse.contains("_id"),
                "Note ID missing in API response"
        );
    }
}
