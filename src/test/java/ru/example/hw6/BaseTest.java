package ru.example.hw6;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
	@BeforeAll
	static void setup() {
		Configuration.browserSize = "1920x1080";
	}
}
