package lesson_eleven.homework11.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;

import static com.google.common.base.CharMatcher.is;
import static org.hamcrest.MatcherAssert.assertThat;


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

