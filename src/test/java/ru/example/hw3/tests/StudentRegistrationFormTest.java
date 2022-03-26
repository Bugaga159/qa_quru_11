package ru.example.hw3.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import ru.example.hw3.pages.PageFormStudents;
import ru.example.hw3.testData.Student;

import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTest extends BaseTest {
    final String PATH_TO_FILE = "src/test/resources/english.jpg";

    @Test
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
                .pathImage(PATH_TO_FILE)
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
                .setUploadPicture(student.getPathImage())
                .setAddress(student.getAddress())
                .setStateAndCity(student.getState(), student.getCity())
                .clickSubmit()
                .assertSuccessfulText("Thanks for submitting the form")
                .assertSuccessfulList(student.getExpRes());
    }

    @Test
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
