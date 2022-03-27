package ru.example.hw9;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideFilesTest {

    @Test
    void selenideDownloadTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadFile = $("#raw-url").download();
        try (InputStream is = new FileInputStream(downloadFile)) {
            assertThat(new String(is.readAllBytes(), StandardCharsets.UTF_8))
                    .contains("This repository is the home of the next generation of JUnit");
        }
    }

    @Test
    void uploadSelenideTest() {
        open("https://the-internet.herokuapp.com/upload");
        $("input[type='file']").uploadFromClasspath("files/1.txt");
        $("#file-submit").click();
        $("div.example").shouldHave(Condition.text("File Uploaded!"));
        $("#uploaded-files").shouldHave(Condition.text("1.txt"));
    }
}
