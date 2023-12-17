package guru.qa.api;

import guru.qa.models.login.LoginRequestModel;
import guru.qa.models.login.LoginResponseModel;

import static guru.qa.specs.Specs.requestSpec;
import static guru.qa.specs.Specs.responseSpec;
import static io.restassured.RestAssured.given;

public class UserApi {
    public LoginResponseModel login(LoginRequestModel loginRequest) {
        return given(requestSpec)
                .body(loginRequest)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
    }
}
