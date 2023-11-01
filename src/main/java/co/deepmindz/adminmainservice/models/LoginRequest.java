package co.deepmindz.adminmainservice.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotEmpty(message = "This field required an username to log in as an Admin")
    private String username;
    @NotEmpty(message = "This field required a password to log in as an Admin")
    private String password;

}
