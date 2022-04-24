package ru.example.hw18.tests;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static ru.example.hw18.helper.CustomAllureListener.withCustomTemplates;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CheckCartAndWishlistTest {
    @BeforeAll
    void setBaseUrl() {
        RestAssured.baseURI = "http://demowebshop.tricentis.com";
    }

    @Test
    @Tag("hw18")
    public void addToCartTest() {
        addThing();
    }

    @Test
    @Tag("hw18")
    public void deleteThingsFromCartTest() {
        String session = addThing();
        Response res = deleteFromCart(session);
        step("Check response that the cart equals 0",() -> {
            XmlPath htmlPath = new XmlPath(HTML, res.getBody().asString());
            assertThat(htmlPath.getString("//span[@class='cart-qty']").equals("0"));
        });


    }

    @Test
    @Tag("hw18")
    void addToWishlistCheck() {
        step("Add to wishlist", () -> {
            given()
                    .filter(withCustomTemplates())
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .body("addtocart_78.EnteredQuantity:=1")
                    .when()
                    .post("/addproducttocart/details/78/2")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("success", is(true))
                    .body("message", is("The product has been added to your " +
                            "<a href=\"/wishlist\">wishlist</a>"))
                    .body("updatetopwishlistsectionhtml", is("(1)"));
        });
    }

    @Step("Add thing to cart and get session")
    private String addThing() {
        return given()
                .filter(withCustomTemplates())
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("addtocart_31.EnteredQuantity=1")
                .when()
                .post("/addproducttocart/details/31/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your " +
                        "<a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(1)"))
                .extract().cookie("Nop.customer");
    }

    @Step("Delete thing from cart")
    private Response deleteFromCart(String session) {
        Response res = given()
                .filter(withCustomTemplates())
                .cookie("Nop.customer", session)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("removefromcart", 2374802)
                .formParam("itemquantity2374802", 1)
                .formParam("updatecart", "Update shopping cart")
                .when()
                .post("/cart")
                .then()
                .contentType(ContentType.HTML)
                .log().all()
                .statusCode(200)
                .extract().response();
        return res;
    }
}
