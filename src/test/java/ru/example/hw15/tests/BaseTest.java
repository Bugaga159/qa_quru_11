package ru.example.hw15.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.example.hw15.config.CredentialsConfig;
import ru.example.hw15.config.Project;
import ru.example.hw15.config.ProjectConfig;
import ru.example.hw15.helper.Attach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {
    @BeforeAll
    @Step("Конфигурируем браузер и удаленный запуск")
    static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
        ProjectConfig browserConfig = ConfigFactory.create(ProjectConfig.class);
        DesiredCapabilities capabilities = new DesiredCapabilities();

        String browser = browserConfig.browser();
        String size = browserConfig.browserSize();
        String browserVersion = browserConfig.browserVersion();

        if(Project.isRemoteWebDriver()) {
            String login = config.login();
            String pass = config.password();
            String url = browserConfig.remoteDriverUrl();
            Configuration.remote = "https://" + login + ":" + pass + "@" + url;

            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
        }

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = browser;
        Configuration.browserSize = size;
        Configuration.browserVersion = browserVersion;
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        if (System.getProperty("api") == null) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();

            if (Project.isRemoteWebDriver()) {
                Attach.addVideo();
            }

            closeWebDriver();
        }
    }
}
