package guru.qa.extensions;

import guru.qa.api.UserApi;
import guru.qa.models.login.LoginRequestModel;
import guru.qa.models.login.LoginResponseModel;
import guru.qa.tests.TestData;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        UserApi userApi = new UserApi();
        LoginRequestModel loginRequest = new LoginRequestModel(TestData.login, TestData.password);
        LoginResponseModel loginResponse = userApi.login(loginRequest);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));
    }
}