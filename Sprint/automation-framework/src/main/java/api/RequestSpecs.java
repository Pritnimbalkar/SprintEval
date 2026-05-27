package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecs {

    public static RequestSpecification getRequestSpec() {

        return new RequestSpecBuilder()

                .setBaseUri(
                        "https://practice.expandtesting.com/notes/api"
                )

                .addHeader(
                        "Content-Type",
                        "application/json"
                )

                .build();
    }
}
