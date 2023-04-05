package com.example.sprint_7.Step;

import com.example.sprint_7.Type.Courier;
import com.example.sprint_7.Type.CourierCredential;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static com.example.sprint_7.Config.Config.COURIER_URL;
import static com.example.sprint_7.Config.Config.getRequestSpec;
import static io.restassured.RestAssured.given;

public class CourierStep {

    @Step("Регистрация курьера")
    public static ValidatableResponse create(Courier courier) {
        return given()
                .spec(getRequestSpec())
                .when()
                .body(courier).log().all()
                .post(COURIER_URL).then().log().all();

    }

    @Step("Авторизация курьера")
    public static ValidatableResponse login(CourierCredential courierCredential) {
        return given()
                .spec(getRequestSpec())
                .when()
                .body(courierCredential).log().all()
                .post(COURIER_URL + "login").then().log().all();

    }

    @Step("Удаление курьера")
    public static ValidatableResponse delete(String courierId) {
        return given()
                .spec(getRequestSpec())
                .when().log().all()
                .delete(COURIER_URL + courierId).then().log().all();

    }

}
