package tests.Auth;
import org.example.api.utils.Auth;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostAuthTest {
    @BeforeAll
    public static void authenticateOnce() {
        Auth.authenticate();
    }

    @Test
    public void testAuthentication() {
        String Token = Auth.getAccessToken();
        assertNotNull(Token, "Токен не должен быть NULL");
        assertFalse(Token.isEmpty(), "Токен не должен быть пустым");

        int dotCount = Token.length() - Token.replace(".", "").length();
        assertTrue(dotCount >= 2, "Токен должен быть в JWT формате");

        System.out.println("Аутентификация успешна.");
        System.out.println("Токен: " + Token);
    }
}
