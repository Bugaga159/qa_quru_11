package ru.example.hw11.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.example.hw11.config.CredentialsConfig;
import ru.example.hw11.config.ProjectConfig;
import ru.example.hw11.helper.Attach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {
    @BeforeAll
    @Step("Конфигурируем браузер и удаленный запуск")
    static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
        ProjectConfig browserConfig = ConfigFactory.create(ProjectConfig.class);

        String login = config.login();
        String pass = config.password();
        String url = browserConfig.remoteDriverUrl();
        String browser = browserConfig.browser();
        String size =browserConfig.browserSize();
        String browserVersion = browserConfig.browserVersion();
        String remoteUrl = "https://" + login + ":" + pass + "@" + url;

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = browser;
        Configuration.browserSize = size;
        Configuration.remote = remoteUrl;
        Configuration.browserVersion = browserVersion;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
