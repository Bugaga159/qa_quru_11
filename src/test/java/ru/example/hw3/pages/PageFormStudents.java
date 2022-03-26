package ru.example.hw3.pages;

import com.codeborne.selenide.SelenideElement;

import java.io.File;
import java.util.HashMap;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PageFormStudents {
    private SelenideElement firstName = $("#firstName");
    private SelenideElement lastName = $("#lastName");
    private SelenideElement userEmail = $("#userEmail");
    private SelenideElement userNumber = $("#userNumber");
    private SelenideElement gender = $("#genterWrapper");
    private SelenideElement subjectsInput = $("#subjectsInput");
    private SelenideElement hobbies = $("#hobbiesWrapper");
    private SelenideElement uploadPicture = $("#uploadPicture");
    private SelenideElement address = $("#currentAddress");
    private SelenideElement submit = $("#submit");
    private SelenideElement successfulText = $("#example-modal-sizes-title-lg");
    private SelenideElement successfulList = $(".table-responsive");

    public PageFormStudents setFirstName(String firstName) {
        this.firstName.setValue(firstName);
        return this;
    }

    public PageFormStudents setLastName(String lastName) {
        this.lastName.setValue(lastName);
        return this;
    }

    public PageFormStudents setUserEmail(String userEmail) {
        this.userEmail.setValue(userEmail);
        return this;
    }

    public PageFormStudents setUserNumber(String userNumber) {
        this.userNumber.setValue(userNumber);
        return this;
    }

    public PageFormStudents setGender(String gender) {
        this.gender.$(byText(gender)).click();
        return this;
    }

    public PageFormStudents setBirthDay(String date) {
        String[] dateBirthday = date.split(" ");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(dateBirthday[1]);
        $(".react-datepicker__year-select").selectOption(dateBirthday[2]);
        $x("//div[contains(@class,'react-datepicker__day--0" + dateBirthday[0]
                +"')][contains(@class,'weekend')]").click();
        return this;
    }

    public PageFormStudents setSubject(String subjectsInput) {
        this.subjectsInput.setValue(subjectsInput).pressEnter();
        return this;
    }

    public PageFormStudents setHobbies(String hobbies) {
        this.hobbies.$(byText(hobbies)).click();
        return this;
    }

    public PageFormStudents setUploadPicture(String path) {
        this.uploadPicture.uploadFile(new File(path));
        return this;
    }

    public PageFormStudents setAddress(String address) {
        this.address.setValue(address);
        return this;
    }

    public PageFormStudents setStateAndCity(String state, String city) {
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
        return this;
    }

    public PageFormStudents clickSubmit() {
        this.submit.scrollTo().click();
        return this;
    }

    public PageFormStudents assertSuccessfulText(String successfulText) {
        this.successfulText.shouldHave(text(successfulText));
        return this;
    }

    public PageFormStudents assertSuccessfulList(HashMap<String, String> map) {
        map.forEach((k, v) -> {
            $x("//td[text()='" + k + "']/following-sibling::td")
                    .shouldHave(text(v));
        });
        return this;
    }
}
