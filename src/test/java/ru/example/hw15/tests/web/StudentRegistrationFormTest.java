package ru.example.hw15.tests.web;

import com.github.javafaker.Faker;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.example.hw15.pages.PageFormStudents;
import ru.example.hw15.testData.Student;
import ru.example.hw15.tests.BaseTest;

import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTest extends BaseTest {

    @Test
    @Tag("hw15")
    @Owner("Panin")
    @DisplayName("Тест заполнения формы регистрации студента все поля")
    @Severity(SeverityLevel.BLOCKER)
    public void inputValidData() {
        Faker faker = new Faker();
        Student student = Student.newBuilder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .userEmail(faker.internet().emailAddress())
                .userNumber("8904117400")
                .gender("Male")
                .dateOfBirth("01 May 1993")
                .subjects("Science")
                .hobbies("Reading")
                .address(faker.address().streetAddress())
                .state("Haryana")
                .city("Panipat").build();

        open("/automation-practice-form", PageFormStudents.class)
                .setFirstName(student.getFirstName())
                .setLastName(student.getLastName())
                .setUserEmail(student.getUserEmail())
                .setUserNumber(student.getUserNumber())
                .setGender(student.getGender())
                .setBirthDay(student.getDateOfBirth())
                .setSubject(student.getSubjects())
                .setHobbies(student.getHobbies())
                .setAddress(student.getAddress())
                .setStateAndCity(student.getState(), student.getCity())
                .clickSubmit()
                .assertSuccessfulText("Thanks for submitting the form")
                .assertSuccessfulList(student.getExpRes());
    }

    @Test
    @Tag("hw15")
    @Owner("Panin")
    @DisplayName("Тест заполнения формы регистрации студента обязательные поля")
    @Severity(SeverityLevel.BLOCKER)
    public void inputRequiredValidData() {
        Faker faker = new Faker();
        Student student = Student.newBuilder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .userNumber("8904117400")
                .gender("Female")
                .dateOfBirth("30 May 1993")
                .build();

        open("/automation-practice-form", PageFormStudents.class)
                .setFirstName(student.getFirstName())
                .setLastName(student.getLastName())
                .setUserNumber(student.getUserNumber())
                .setGender(student.getGender())
                .setBirthDay(student.getDateOfBirth())
                .clickSubmit()
                .assertSuccessfulText("Thanks for submitting the form")
                .assertSuccessfulList(student.getExpRes());
    }
}
