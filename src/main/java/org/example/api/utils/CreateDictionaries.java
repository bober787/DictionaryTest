package org.example.api.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.api.config.ApiConfig;
import org.example.api.data.Variables;

public class CreateDictionaries {

    public static Response createDictionary(String name, String langPair,
                                            String description, boolean isPrivate) {
        return RestAssured.given()
                .baseUri(Variables.BASE_URL)
                .header(Variables.AUTHORIZATION,
                        Variables.AUTHORIZATION_TYPE + Auth.getAccessToken())
                .contentType("multipart/form-data")
                .multiPart("name", name)
                .multiPart("lang_pair", langPair)  // исправлено с lang_chain на lang_pair
                .multiPart("description", description)
                .multiPart("is_private", isPrivate)  // передаем boolean как есть
                .when()
                .post(ApiConfig.DICTIONARIES)
                .then()
                .extract()
                .response();
    }

}
