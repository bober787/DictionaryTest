package tests.Auth;

import org.example.api.utils.RefreshToken;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostRefreshTokenTest {
    private static String newToken;

    @BeforeAll
    public static void setup() {
        System.out.println("Обновляем токен перед тестами...");
        newToken = RefreshToken.updateToken();
    }

    @Test
    public void testRefreshedTokenValidity() {
        assertNotNull(newToken, "Токен не должен быть null");
        assertFalse(newToken.isEmpty(), "Токен не должен быть пустым");

        int dotCount = newToken.length() - newToken.replace(".", "").length();
        assertTrue(dotCount >= 2, "Токен должен быть в JWT формате");

        System.out.println("Токен обновлен.");
        System.out.println("Новый токен: " + newToken);
    }
}