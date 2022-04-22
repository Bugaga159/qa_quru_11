package ru.example.hw17.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import ru.example.hw17.config.ProjectConfig;

public class BaseTest {
    @BeforeAll
    void setBefore() {
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class);
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri(config.baseUrl())
                .setAccept(ContentType.JSON)
                .build();
        RestAssured.requestSpecification = requestSpec;
    }

}
