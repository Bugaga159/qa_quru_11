package ru.example;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTest {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    public void shouldSeeFullOfForm() {
        open("/automation-practice-form");

        $("#firstName").setValue("Igor");
        $("#lastName").setValue("Bulkin");
        $("#userEmail").setValue("example@test.org");
        $("#userNumber").setValue("8904117400");
        $("#genterWrapper").$(byText("Male")).click();

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("1993");
        $(".react-datepicker__day--001").click();

        $("#subjectsInput").setValue("English").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/english.jpg"));
        $("#currentAddress").setValue("Test address");
        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("Karnal").pressEnter();
        $("#submit").scrollTo().click();
        // Assert
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text("Igor Bulkin"),
                text("example@test.org"),
                text("Male"),
                text("8904117400"),
                text("01 May,1993"),
                text("English"),
                text("Sports"),
                text("english.jpg"),
                text("Test address"),
                text("Haryana Karnal")
        );
    }
}
