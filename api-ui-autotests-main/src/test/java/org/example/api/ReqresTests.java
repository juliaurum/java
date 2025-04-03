package org.example.api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.SoftAssertions;
import org.example.api.spec.Specifications;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ReqresTests {

    private final static String URL = "https://reqres.in/";

    @Tag("positive")
    @DisplayName("Успешная регистрация")
    @Test
    public void successUserRegistration() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(200));
        Map<String, String> user = new HashMap();
        user.put("email", "eve.holt@reqres.in");
        user.put("password", "pistol");
        Response response = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("id");
        String token = jsonPath.get("token");
        Assertions.assertEquals(4, id, "Id зарегистрированного пользователя не равен '4'");
        Assertions.assertEquals("QpwL5tke4Pnpja7X4", token, "Токен пользователя отличается от ожидаемого");
    }

    @Tag("negative")
    @DisplayName("Регистрация с пустым паролем невозможна")
    @Test
    public void userRegistrationWithEmptyPassword() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(400));
        Map<String, String> user = new HashMap();
        user.put("email", "sydney@fife");
        Response response = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Assertions.assertEquals("Missing password", jsonPath.get("error"));
    }

    @Tag("positive")
    @DisplayName("У каждого пользователя из списка email заканчивается на @reqres.in")
    @Test
    public void everyEmailHasDomainInUsersList() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(200));
        Response response = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .body("page", equalTo(2))
                .body("data.email", notNullValue())
                .extract().response();
        List<String> emails = response.jsonPath().get("data.email");
        for (String email : emails) {
            Assertions.assertTrue(email.endsWith("@reqres.in"), "У одного из пользователей email: "
                    + email + ", то есть не заканчивается на '@reqres.in'");
        }
    }

    /**
     * Так как у меня нет уверенности, что в api-документацию своевременно вносятся правки, то возможно ручка
     * на удаление пользователя уже обновлена, возвращает 200й статус-код и имеет тело ответа, в котором данные удаленного
     * пользователя. Пока деактивирую данный тест через @Disabled
     **/
    @Tag("positive")
    @DisplayName("Удаление второго пользователя")
    @Disabled
    @Test
    public void delete2User() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(200));
        Response response = given()
                .when()
                .get("api/users/2")
                .then().log().all()
                .body("data.id", equalTo(2))
                .body("data.email", notNullValue())
                .body("data.first_name", notNullValue())
                .body("data.last_name", notNullValue())
                .body("data.avatar", notNullValue())
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Assertions.assertEquals(2, (int) jsonPath.get("data.id"), "Id удаленного пользователя не равен '2'");
        Assertions.assertEquals("Janet", jsonPath.get("data.first_name"), "Имя удаленного пользователя не 'Janet'");
        Assertions.assertEquals("Weaver", jsonPath.get("data.last_name"), "Фамилия удаленного пользователя не 'Weaver'");
    }

    @Tag("positive")
    @DisplayName("Удаление второго пользователя с пустым телом ответа")
    @Test
    public void delete2UserWithoutRespBody() {
        Specifications.installSpecification(Specifications.requestSpec(URL));
        ValidatableResponse validatableResponse = given()
                .when()
                .get("api/users/2")
                .then();
        int statusCode = validatableResponse.extract().statusCode();
        String response = validatableResponse.extract().body().asString();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.isEmpty()).withFailMessage("Тело ответа непустое:\n " + response).isTrue();
        softly.assertThat(statusCode).withFailMessage("Фактический статус-код ответа: " + statusCode)
                .isEqualTo(204);
        softly.assertAll();
    }
}
