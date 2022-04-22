package ru.example.hw17.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.example.hw17.models.*;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApiTest extends BaseTest {

    @Test
    @Tag("apiHw17")
    @DisplayName("GET /api/unknown - 200")
    void checkListSourcesTest() {
        RespResourcesDto res = given().get("/api/unknown")
                .then().statusCode(200)
                .extract().as(RespResourcesDto.class);
        assertThat(res.getPage() == 1);
        assertThat(res.getPerPage() == 6);
        assertThat(res.getData().size() == 6);
        assertThat(res.getData().stream().filter(r -> r.getName().contains("tigerlily")).count() == 1);
        assertThat(res.getSupport().getText().equals("To keep ReqRes free," +
                " contributions towards server costs are appreciated!"));
    }

    @Test
    @Tag("apiHw17")
    @DisplayName("GET /api/unknown/2 - 200")
    void checkSourceByIdTest() {
        RespSingleResourceDto res = given().get("/api/unknown/2")
                .then().statusCode(200)
                .extract().as(RespSingleResourceDto.class);
        assertThat(res.getData().getId() == 2);
        assertThat(res.getData().getName().equals("fuchsia rose"));
        assertThat(res.getData().getColor().equals("color"));
        assertThat(res.getData().getPantoneValue().equals("17-2031"));
        assertThat(res.getData().getYear() == 2001);
        assertThat(res.getSupport().getUrl().equals("https://reqres.in/#support-heading"));
        assertThat(res.getSupport().getText().equals("To keep ReqRes free," +
                " contributions towards server costs are appreciated!"));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 90})
    @Tag("apiHw17")
    @DisplayName("GET /api/unknown/90 - 404")
    void checkSourceByInvalidIdTest(Integer num) {
        given().get("/api/unknown/" + num)
                .then().statusCode(404);
    }

    @Test
    @Tag("apiHw17")
    @DisplayName("POST /api/users - 201")
    void createUserTest() {
        Faker faker = new Faker();
        UserDto user = UserDto.builder()
                .name(faker.name().firstName())
                .job(faker.job().position())
                .build();
        RespUsersDto res = given()
                .contentType(JSON)
                .body(user)
                .post("/api/users")
                .then().statusCode(201)
                .extract().as(RespUsersDto.class);
        assertThat(res != null);
        assertThat(!res.getId().equals(""));
        assertThat(!res.getCreatedAt().equals(""));
        assertThat(res.getName().equals(user.getName()));
        assertThat(res.getJob().equals(user.getJob()));
    }

    @Test
    @Tag("apiHw17")
    @DisplayName("POST /api/login - 200")
    void loginTest() {
        LoginDto user = LoginDto.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();
        TokenDto res = given()
                .contentType(JSON)
                .body(user)
                .post("/api/login")
                .then().statusCode(200)
                .extract().as(TokenDto.class);
        System.out.println(res);
        assertThat(res.getToken() != null);
    }
}
