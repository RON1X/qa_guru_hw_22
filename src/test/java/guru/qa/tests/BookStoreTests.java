package guru.qa.tests;

import guru.qa.api.BookApi;
import guru.qa.api.UserApi;
import guru.qa.extensions.WithLogin;
import guru.qa.models.book.AddBookRequestModel;
import guru.qa.models.book.DeleteBookRequestModel;
import guru.qa.models.book.IsbnModel;
import guru.qa.models.login.LoginRequestModel;
import guru.qa.models.login.LoginResponseModel;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class BookStoreTests extends TestBase {

    UserApi userApi = new UserApi();
    BookApi bookApi = new BookApi();

    LoginRequestModel loginRequest = new LoginRequestModel(TestData.login, TestData.password);
    LoginResponseModel loginResponse = userApi.login(loginRequest);

    @Test
    @DisplayName("Авторизация через UI")
    @Epic("Авторизация")
    void loginWithUITest() {
        step("Открыть страницу авторизации", () ->
                open("/login"));
        step("Ввести логин и пароль", () -> {
                $("#userName").setValue(TestData.login);
                $("#password").setValue(TestData.password);
        });
        step("Нажать кнопку Login", () ->
                $("#login").click());
        step("Проверить ник в поле User Name", () ->
                $("#userName-value").shouldHave(text(TestData.login)));
    }

    @Test
    @DisplayName("Авторизация по API")
    @Epic("Авторизация")
    @WithLogin
    void loginWithApiTest() {
        step("Открыть профиль", () ->
                open("/profile"));
        step("Проверить ник в поле User Name", () ->
                $("#userName-value").shouldHave(text(TestData.login)));
    }

    @Test
    @DisplayName("Удаление книги")
    @Epic("Профиль")
    @WithLogin
    void addBook() {
        step("Удалить все книги", () ->
                bookApi.deleteAllBook(loginResponse));
        step("Добавить книгу", () -> {
                List<IsbnModel> isbnList = new ArrayList<>();
                IsbnModel isbn = new IsbnModel(TestData.isbn);
                isbnList.add(isbn);
                bookApi.addBook(loginResponse, new AddBookRequestModel(loginResponse.getUserId(), isbnList));
        });
        step("Удалить книгу", () ->
                bookApi.deleteBook(loginResponse, new DeleteBookRequestModel(TestData.isbn, loginResponse.getUserId())));
        step("Проверить что книги нет в таблице", () -> {
                open("/profile");
                $(".rt-noData").shouldBe(visible);
                $(".rt-noData").shouldHave(text("No rows found"));
        });
    }
}