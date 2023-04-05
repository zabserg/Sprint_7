package com.example.sprint_7.Step;

import com.example.sprint_7.Type.Order;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static com.example.sprint_7.Config.Config.ORDERS_URL;
import static com.example.sprint_7.Config.Config.getRequestSpec;
import static io.restassured.RestAssured.given;

public class OrderStep {

    @Step("Создание заказа")
    public static ValidatableResponse createOrder(Order order) {
        return given()
                .spec(getRequestSpec())
                .body(order)
                .when()
                .post(ORDERS_URL).then().log().all();
    }

    @Step("Получение списка всех заказов")
    public static ValidatableResponse getOrders() {
        return given()
                .spec(getRequestSpec())
                .when()
                .get(ORDERS_URL).then().log().all();
    }
}
