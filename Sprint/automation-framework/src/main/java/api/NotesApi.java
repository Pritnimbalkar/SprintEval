package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class NotesApi {

    // Login API

    public static String getToken() {

    String loginPayload =
            "{"
            + "\"email\":\"testuser1+1@gmail.com\","
            + "\"password\":\"user1A@x\""
            + "}";

    Response response = given()

            .spec(RequestSpecs.getRequestSpec())

            .body(loginPayload)

    .when()

            .post("/users/login");

    System.out.println("STATUS CODE:");
    System.out.println(response.getStatusCode());

    System.out.println("RESPONSE BODY:");
    System.out.println(response.getBody().asString());

    return response.jsonPath()
            
            .getString("data.token");
    }

    // GET Notes API

    public static Response getNotes() {

        String token = getToken();

        return given()

                .spec(RequestSpecs.getRequestSpec())

                .header(
                    "x-auth-token",
                    token
                )

        .when()

                .get("/notes");
    }

    public static String getNotesResponse() {

    Response response =
            getNotes();

    return response.asString();
    }

    public static Response deleteNote(String noteId) {

    String token =
            getToken();

    return given()

            .spec(RequestSpecs.getRequestSpec())

            .header(
                    "x-auth-token",
                    token
            )

    .when()

            .delete("/notes/" + noteId);
    }

    public static String getLatestNoteId() {

    Response response =
            getNotes();

    response.prettyPrint();

    String noteId =
            response.jsonPath()
                    .getString("data[0].id");



    return noteId;
    }

    

    public static Response getNotesWithToken(
        String token
    )    
    {

    return given()

            .spec(
                    RequestSpecs
                            .getRequestSpec()
            )

            .header(
                    "x-auth-token",
                    token
            )

    .when()

            .get("/notes");
    }
}