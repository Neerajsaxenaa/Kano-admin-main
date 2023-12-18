package co.deepmindz.adminmainservice.dto;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminDto {
    private String userId;

    @NotEmpty(message = "This field required an Email id to be registered as an Admin")
    @Email(message = "Email address should be valid ")
    private String userName;
    
    @NotEmpty
    @NotNull
    private String name;
    
    @NotEmpty(message = "Password can not be null ")
    private String password;
    
    private String userRole;
    
    private String status;
    
    private String createdAt;
    
    private List<String> actions;
	
    
}
