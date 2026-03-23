package com.mahd;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ForexResourceTest {

    @Test
    void testConvertEndpoint() {
        given()
                .queryParam("amount", 100)
                .queryParam("from", "USD")
                .queryParam("to", "UGX")
                .when()
                .get("/convert")
                .then()
                .statusCode(200)
                .body("result", is(380000.0f));
    }

    @Test
    void testInvalidAmount() {
        given()
                .queryParam("amount", -100)
                .queryParam("from", "USD")
                .queryParam("to", "UGX")
                .when()
                .get("/convert")
                .then()
                .statusCode(400)
                .body("message", is("Amount must be greater than 0"));
    }

    @Test
    void testUnsupportedCurrency() {
        given()
                .queryParam("amount", 100)
                .queryParam("from", "USD")
                .queryParam("to", "XYZ")
                .when()
                .get("/convert")
                .then()
                .statusCode(400);
    }
}