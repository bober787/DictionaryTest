package org.example.api.utils;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.api.config.ApiConfig;
import org.example.api.data.Variables;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RefreshToken {
    private static final Dotenv dotenv = Dotenv.load();

    public static String updateToken() {
        String refreshToken = extractTokenFromJson(dotenv.get("API_TOKEN"), VariablesREFRESH_TOKEN);

        Response response = RestAssured.given()
                .baseUri(Variables.BASE_URL)
                .queryParam(Variables.REFRESH_TOKEN, refreshToken)
                .when()
                .post(ApiConfig.REFRESH_TOKEN)
                .then()
                .extract()
                .response();

        if (response.statusCode() == 200) {
            String newAccessToken = extractTokenFromJson(response.asString(), Variables.ACCESS_TOKEN);
            updateEnvFile(newAccessToken);
            return newAccessToken;
        } else {
            throw new RuntimeException("Ошибка обновления токена. Status code: " + response.statusCode());
        }
    }

    private static String extractTokenFromJson(String json, String tokenType) {
        if (json == null) return null;

        Pattern pattern = Pattern.compile("\"" + tokenType + "\":\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return json;
    }

    private static void updateEnvFile(String newToken) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(".env"));

            List<String> updatedLines = lines.stream()
                    .map(line -> line.startsWith("API_TOKEN=")
                            ? "API_TOKEN=" + newToken
                            : line)
                    .collect(Collectors.toList());

            Files.write(Paths.get(".env"), updatedLines);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка обновления .env файла", e);
        }
    }
}