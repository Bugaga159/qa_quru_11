package ru.example.hw5;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
    }
}
