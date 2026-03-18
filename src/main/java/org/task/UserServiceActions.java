package org.task;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.task.POJO.CreateUserBodyPojo;

import static io.restassured.RestAssured.given;

public class UserServiceActions {
    private final ApiClient apiClient;
    private static final String users = "/api/v1/users/{id}";
    private static final String baseUrl = "http://localhost:8080";
    private final RequestSpecification requestSpec;


    public UserServiceActions(ApiClient apiClient) {
        super();
        this.apiClient = apiClient;
        requestSpec = new RequestSpecBuilder()
                .setConfig(this.apiClient.config)
                .setBaseUri(baseUrl)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
    }


    public Response createUser(CreateUserBodyPojo payload) {
        return given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .post("/api/v1/users");
    }

    public Response getUserById(String userId) {
        return given()
                .spec(requestSpec)
                .pathParam("id", userId)
                .when()
                .get(users);
    }

    public Response deleteUser(String userId) {
        return given()
                .spec(requestSpec)
                .pathParam("id", userId)
                .when()
                .delete(users);
    }
}
