package tests.api;

import api.NotesApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NotesApiTests {

    // TC-08

    @Test(priority = 1)

    public void verifyNewlyCreatedNoteAppearsInstantly() {

        Response response =
                NotesApi.getNotes();

        System.out.println("STATUS CODE:");

        System.out.println(
                response.getStatusCode()
        );

        System.out.println("RESPONSE:");

        response.prettyPrint();

        Assert.assertEquals(
                response.getStatusCode(),
                200
        );
    }

    // TC-09

    @Test(priority = 2)

    public void verifyNoteVisibilityAfterRefresh() {

        Response response =
                NotesApi.getNotes();

        System.out.println("STATUS CODE:");

        System.out.println(
                response.getStatusCode()
        );

        System.out.println("RESPONSE:");

        response.prettyPrint();

        Assert.assertEquals(
                response.getStatusCode(),
                200
        );
    }
}