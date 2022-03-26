package ru.example.hw6;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class ListenerInSelenide extends BaseTest{

	@Test
	public void findIssueIdByListener() {
		SelenideLogger.addListener("allure", new AllureSelenide());
		open("https://github.com");

		$(".header-search-input").click();
		$(".header-search-input").sendKeys("eroshenkoam/allure-example");
		$(".header-search-input").submit();

		$(linkText("eroshenkoam/allure-example")).click();
		$(partialLinkText("Issues")).click();
		$(withText("#68")).should(Condition.visible);
	}
}
