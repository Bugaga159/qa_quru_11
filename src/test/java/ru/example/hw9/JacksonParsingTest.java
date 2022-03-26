package ru.example.hw9;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.example.hw9.domain.Teacher;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonParsingTest {

    ClassLoader classLoader = getClass().getClassLoader();
    final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void jsonCommonTest() throws IOException {
            Teacher teacher = objectMapper
                    .readValue(classLoader.getResourceAsStream("files/simple.json"), Teacher.class);
            assertThat(teacher.name).isEqualTo("Dmitrii");
            assertThat(teacher.address.street).isEqualTo("Mira");
        }
}
