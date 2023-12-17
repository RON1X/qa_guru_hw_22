package guru.qa.models.login;

import lombok.Data;

@Data
public class LoginRequestModel {

    private String userName, password;
    public LoginRequestModel(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}