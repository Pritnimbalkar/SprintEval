package tests.api;

import api.NotesApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NotesPerformanceApiTests {

@Test(priority = 1)
public void verifyApiResponseTime() {

    String token =
            NotesApi.getToken();

    long startTime =
            System.currentTimeMillis();

    Response response =
            NotesApi.getNotesWithToken(token);

    long endTime =
            System.currentTimeMillis();

    long responseTime =
            endTime - startTime;

    System.out.println(
            "Actual API Response Time: "
            + responseTime
            + " ms"
    );

    Assert.assertTrue(
            responseTime < 2000,
            "API response exceeded 2 seconds"
    );
    }

    @Test(priority = 2)

    public void verifyApiStabilityDuringMultipleRequests() {

        for (int i = 1; i <= 8; i++) {

            Response response =
                    NotesApi.getNotes();

            System.out.println(
                    "Request "
                    + i
                    + " Status Code: "
                    + response.getStatusCode()
            );

            Assert.assertEquals(
                    response.getStatusCode(),
                    200,
                    "API unstable during multiple requests"
            );
        }
    }
}
