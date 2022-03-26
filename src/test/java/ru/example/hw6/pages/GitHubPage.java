package ru.example.hw6.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class GitHubPage {

	@Step("Открытие браузера и переход в {url}")
	public GitHubPage openPage(String url) {
		open(url, GitHubPage.class);
		return this;
	}

	@Step("Поиск репозитория {value}")
	public GitHubPage searchRepository(String value) {
	$(".header-search-input").click();
	$(".header-search-input").sendKeys(value);
	$(".header-search-input").submit();
		return this;
	}

	@Step("Переход в репозиторий {value}")
	public GitHubPage selectRepository(String value) {
		$(linkText(value)).click();
		return this;
	}

	@Step("Переход в 'Issues'")
	public GitHubPage selectIssues() {
		$(partialLinkText("Issues")).click();
		return this;
	}

	@Step("Поиск issue с id {id}")
	public GitHubPage assertIssueId(int id) {
		$(withText("#" + id)).should(Condition.visible);
		return this;
	}
}
