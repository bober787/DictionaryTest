package tests.User;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.api.config.ApiConfig;
import org.example.api.data.Variables;
import org.example.api.utils.Auth;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.example.api.data.Variables.BASE_URL;

public class GetUserProfileTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        Auth.authenticate();
    }

    @Test
    public void testGetProfile() {
        Response profileResponse = given()
                .header(Variables.AUTHORIZATION, Variables.AUTHORIZATION_TYPE + Auth.getAccessToken())
                .when()
                .get(ApiConfig.USER_PROFILE)
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Ответ от GET/user/profile:");
        System.out.println(profileResponse.asPrettyString());

        String username = profileResponse.jsonPath().getString(Variables.JSON_FIELD_USERNAME);
        int id = profileResponse.jsonPath().getInt(Variables.JSON_FIELD_ID);
        String email = profileResponse.jsonPath().getString(Variables.JSON_FIELD_EMAIL);

        Variables.USERNAME = username;
        Variables.ID = String.valueOf(id);
        Variables.EMAIL = email;

        System.out.println("\nИзвлечённые данные:");
        System.out.println(Variables.JSON_FIELD_USERNAME + " = " + username);
        System.out.println(Variables.JSON_FIELD_ID + " = " + id);
        System.out.println(Variables.JSON_FIELD_EMAIL + " = " + email);
    }
}