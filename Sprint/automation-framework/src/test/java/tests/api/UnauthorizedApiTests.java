package tests.api;

import api.RequestSpecs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UnauthorizedApiTests {

    @Test

    public void verifyUnauthorizedApiRequestHandling() {

        // Call API WITHOUT token

        Response response =

                given()

                        .spec(
                                RequestSpecs
                                        .getRequestSpec()
                        )

                .when()

                        .get("/notes");

        // Print response

        System.out.println(
                "STATUS CODE:"
        );

        System.out.println(
                response.getStatusCode()
        );

        System.out.println(
                "RESPONSE BODY:"
        );

        System.out.println(
                response.asString()
        );

        // Validation

        Assert.assertEquals(
                response.getStatusCode(),
                401,
                "Unauthorized request not handled properly"
        );
    }
}
