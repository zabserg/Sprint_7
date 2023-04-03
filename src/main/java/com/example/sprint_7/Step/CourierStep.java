package com.example.sprint_7.Step;

import com.example.sprint_7.Type.Courier;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static com.example.sprint_7.Config.Config.COURIER_URL;
import static com.example.sprint_7.Config.Config.getRqSpec;
import static io.restassured.RestAssured.given;

public class CourierStep {

    @Step("Регистрация курьера")
    public static ValidatableResponse create(Courier courier) {
        return given()
                .spec(getRqSpec())
                .when()
                .body(courier).log().all()
                .post(COURIER_URL).then().log().all();

    }


}
