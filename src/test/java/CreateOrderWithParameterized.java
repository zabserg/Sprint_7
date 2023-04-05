import com.example.sprint_7.Step.OrderStep;
import com.example.sprint_7.Type.Order;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderWithParameterized {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    public CreateOrderWithParameterized(String firstName, String lastName, String address, String metroStation,
                                        String phone, int rentTime, String deliveryDate, String comment,
                                        List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Заказчик", "Заказчиков", "Ленина 1", "Станция 1", "+79001234567", 1, "01-02-2023",
                        "comment", List.of("Black")},
                {"Customer", "Curtomerov", "пр-кт Маркса 12", "Station 2", "8 900 123 45 67", 2, "01-03-2023",
                        "", List.of("Gray")},
                {"Клиент_12345", "Клиентов", "улица Победы 8", "@#$%^", "89001234567", 3, "01-04-2023",
                        "комментарий", List.of("Black", "Gray")},
                {"!@#$%^&*()", "!@#$%^&*()", "Красной Армии", "", "+7(900)1234567", 4, "31-03-2023",
                        ";%:??", null},
        };
    }

    @Test
    @DisplayName("Успешное создание заказа с валидными данными")
    public void successfullyCreateOrderWitchValidDataTest() {
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        ValidatableResponse response = OrderStep.createOrder(order);
        response.statusCode(201)
                .assertThat()
                .body("track", notNullValue());
    }
}
