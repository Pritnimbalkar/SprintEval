package tests.api;

import api.NotesApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NotesDeleteApiTests {

    @Test(priority = 1)

    public void verifySuccessfulNoteDeletion() {

        // Existing Note ID

        String noteId =
        NotesApi.getLatestNoteId();

        // Delete API call

        Response response =
                NotesApi.deleteNote(noteId);

        // Validation

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Note deletion failed"
        );
    }

    @Test(priority = 2)

    public void verifyInvalidNoteIdDeletion() {

        // Invalid note ID

        String invalidId =
                "invalid123";

        // Delete API call

        Response response =
                NotesApi.deleteNote(invalidId);
                System.out.println(response.asString());

        // Validation

        Assert.assertTrue(
                response.getStatusCode() == 400
                || response.getStatusCode() == 404,
                "Unexpected response for invalid note ID"
        );
    }
}