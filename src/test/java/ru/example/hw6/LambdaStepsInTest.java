package ru.example.hw6;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class LambdaStepsInTest extends BaseTest{

	@Test
	public void findIssueIdByLambda() {
		step("Открытие браузера",() -> open("https://github.com"));
		step("Поиск репозитория eroshenkoam/allure-example", () -> {
			$(".header-search-input").click();
			$(".header-search-input").sendKeys("eroshenkoam/allure-example");
			$(".header-search-input").submit();
		});
		step("Переход в репозиторий eroshenkoam/allure-example",() ->
				$(linkText("eroshenkoam/allure-example")).click()
		);
		step("Переход в 'Issues'",() ->
				$(partialLinkText("Issues")).click()
		);
		step("Поиск issue с id 68",() ->
				$(withText("#68")).should(Condition.visible)
		);
	}
}
