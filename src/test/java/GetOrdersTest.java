import com.example.sprint_7.Step.OrderStep;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersTest {

    @Test
    @DisplayName("Получение списка всех заказов")
    public void getAllOrdersTest(){
        ValidatableResponse response = OrderStep.getOrders();
        response.statusCode(200)
                .assertThat()
                .body("orders", notNullValue());

    }
}
