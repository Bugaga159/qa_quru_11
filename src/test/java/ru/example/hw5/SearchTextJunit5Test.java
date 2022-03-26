package ru.example.hw5;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchTextJunit5Test extends BaseTest {

	@Test
	public void shouldBeTextJunit5() {
		open("/selenide/selenide");
		$x("//*[@data-content='Wiki']").click();
		$(".wiki-more-pages-link button").click();
		$(byText("SoftAssertions")).click();
		$x("//a[@id='user-content-3-using-junit5-extend-test-class']/..")
				.shouldHave(text("Using JUnit5 extend test class:"));
	}
}
