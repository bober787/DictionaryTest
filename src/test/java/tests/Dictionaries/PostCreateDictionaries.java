package tests.Dictionaries;

import org.example.api.utils.Auth;
import org.example.api.utils.CreateDictionaries;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PostCreateDictionaries {
    @BeforeAll
    public static void setup() {
        Auth.authenticate();
    }

    @Test
    public void testCreateDictionary() {
        String dictionaryName = "Test Dictionary";
        String langPair = "en-ru";
        String description = "Test description";
        boolean isPrivate = true;

        var response = CreateDictionaries.createDictionary(
                dictionaryName, langPair, description, isPrivate);

        assertEquals(200, response.statusCode(), "Status code should be 200");
        assertNotNull(response.asString(), "Response should not be null");

        System.out.println("Dictionary created successfully. Response: " + response.asString());
    }
}
