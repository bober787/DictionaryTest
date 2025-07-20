package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    private static String accessToken;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8000";
        // Включаем подробное логирование при ошибках
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void testLoginAndGetProfile() {
        // --- 1. Авторизация ---
        System.out.println("=== Отправляем запрос на авторизацию ===");
        Response loginResponse = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "password")
                .formParam("username", "admintest")
                .formParam("password", "admintest")
                .when()
                .post("/auth/token")
                .then()
                .statusCode(200)
                .body("access_token", notNullValue())
                .extract().response();

        // Выводим полный ответ от сервера
        System.out.println("Ответ от /auth/token:");
        System.out.println(loginResponse.asPrettyString());

        accessToken = loginResponse.jsonPath().getString("access_token");
        System.out.println("Получен токен: " + accessToken);

        // --- 2. Запрос профиля ---
        System.out.println("\n=== Запрашиваем данные профиля ===");
        Response profileResponse = given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/user/profile")
                .then()
                .statusCode(200)
                .extract().response();

        // Выводим полный ответ
        System.out.println("Ответ от /user/profile:");
        System.out.println(profileResponse.asPrettyString());

        // Проверяем конкретные поля
        String username = profileResponse.jsonPath().getString("username");
        int id = profileResponse.jsonPath().getInt("id");
        System.out.println("\nИзвлечённые данные: username=" + username + ", id=" + id);
    }
}