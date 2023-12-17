package guru.qa.api;

import guru.qa.models.book.AddBookRequestModel;
import guru.qa.models.book.DeleteBookRequestModel;
import guru.qa.models.login.LoginResponseModel;

import static guru.qa.specs.Specs.requestSpec;
import static guru.qa.specs.Specs.responseSpec;
import static io.restassured.RestAssured.given;

public class BookApi {
    public void addBook(LoginResponseModel loginResponse, AddBookRequestModel addBookRequest) {
        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(addBookRequest)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseSpec)
                .statusCode(201);
    }

    public void deleteAllBook(LoginResponseModel loginResponse) {
        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(responseSpec)
                .statusCode(204);
    }

    public void deleteBook(LoginResponseModel loginResponse, DeleteBookRequestModel deleteBookRequest) {
        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(deleteBookRequest)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(responseSpec)
                .statusCode(204);
    }
}