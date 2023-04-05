import com.example.sprint_7.Step.CourierStep;
import com.example.sprint_7.Type.Courier;
import com.example.sprint_7.Type.CourierCredential;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CreateCourierTest {

    Courier courier;
    String id;

    @Before
    public void setUp() {
        courier = Courier.generateCourierRandom();
    }

    @After
    public void cleanUp() {
        if (id != null) {
            CourierStep.delete(id);
        }
    }

    @Test
    @DisplayName("Успешная регистрация курьера")
    public void successfullyCourierCreationTest() {
        ValidatableResponse regResponse = CourierStep.create(courier);
        ValidatableResponse loginResponse = CourierStep.login(CourierCredential.getCredentials(courier));
        id = loginResponse.extract().path("id").toString();
        regResponse
                .statusCode(201)
                .assertThat().body("ok", is(true));
    }

    @Test
    @DisplayName("Регистрация курьеров с одинаковыим логином")
    public void duplicateCourierCreationTest(){
        CourierStep.create(courier);
        id = CourierStep.login(CourierCredential.getCredentials(courier))
                .extract().path("id").toString();

        ValidatableResponse response = CourierStep.create(courier);
        response
                .statusCode(409)
                .assertThat()
                .body("code",equalTo(409))
                .and()
                .body("message",equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Регистрация курьера без логина")
    public void courierCreationWithoutLoginTest(){
        courier.setLogin(null);
        ValidatableResponse response = CourierStep.create(courier);
        response
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Регистрация курьера без пароля")
    public void courierCreationWithoutPasswordTest(){
        courier.setPassword(null);
        ValidatableResponse response = CourierStep.create(courier);
        response
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Успешная регистрация курьера без firstName")
    public void SuccessfullyCourierCreationWithoutFirstNameTest(){
        courier.setFirstName(null);
        ValidatableResponse regResponse = CourierStep.create(courier);
        ValidatableResponse loginResponse = CourierStep.login(CourierCredential.getCredentials(courier));
        id = loginResponse.extract().path("id").toString();
        regResponse
                .statusCode(201)
                .assertThat().body("ok", is(true));
    }
}
