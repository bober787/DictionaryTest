package org.example.api.utils;

import io.restassured.response.Response;
import lombok.Getter;
import org.example.api.config.ApiConfig;
import org.example.api.data.Variables;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class Auth {
    @Getter
    private static String accessToken;

    public static void authenticate() {
        Response response = given()
                .baseUri(Variables.BASE_URL)
                .contentType(Variables.PARAM_CONTENT_TYPE)
                .formParam(Variables.PARAM_GRANT_TYPE, Variables.PARAM_GRANT_TYPE_PASSWORD)
                .formParam(Variables.PARAM_USERNAME, Variables.LOGIN)
                .formParam(Variables.PARAM_PASSWORD, Variables.PASSWORD)
                .formParam(Variables.PARAM_CLIENT_ID, Variables.CLIENT_ID)
                .formParam(Variables.PARAM_CLIENT_SECRET, Variables.CLIENT_SECRET)
                .when()
                .post(ApiConfig.AUTH_TOKEN)
                .then()
                .statusCode(200)
                .body(Variables.JSON_ACCESS_TOKEN, notNullValue())
                .extract()
                .response();

        accessToken = response.jsonPath().getString(Variables.JSON_ACCESS_TOKEN);
        writeTokenToEnv(accessToken);

        System.out.println("Успешная аутентификация");
        System.out.println("Токен получен: " + accessToken);
    }

    private static void writeTokenToEnv(String token) {
        try {
            String envPath = Paths.get(System.getProperty("user.dir"), ".env").toString();
            FileWriter writer = new FileWriter(envPath);
            writer.write("API_TOKEN=" + token);
            writer.close();
        } catch (IOException e) {
            System.err.println("Ошибка при записи токена: " + e.getMessage());
        }
    }

}