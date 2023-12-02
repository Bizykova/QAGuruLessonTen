package lesson_eleven.homework11;

import com.fasterxml.jackson.databind.ObjectMapper;
import lesson_eleven.homework11.json.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;


public class JsonTest {
    private ClassLoader cl = JsonTest.class.getClassLoader();

    @Test
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("users.json")
        )) {

            ObjectMapper objectMapper = new ObjectMapper();
            User newUser = objectMapper.readValue(reader, User.class);

            Assertions.assertEquals(newUser.getFirstName(), "Masha");
            Assertions.assertEquals(newUser.getLastName(), "Busya");
            Assertions.assertEquals(newUser.getPhone(), "8976999");
            Assertions.assertEquals(newUser.getPets().getName(), "Rex");
        }
    }
}

