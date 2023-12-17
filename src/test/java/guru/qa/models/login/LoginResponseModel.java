package guru.qa.models.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponseModel {
    String userId, username, password, token, expires;
    @JsonProperty("created_date")
    String createdDate;
    String isActive;
}
