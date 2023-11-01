package co.deepmindz.adminmainservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class AdminDto {
    private String userId;

    @NotEmpty(message = "This field required an Email id to be registered as an Admin")
    @Email(message = "Email address should be valid ")
    private String userName;
    @NotEmpty(message = "Password can not be null ")
    private String password;
    private String userRole;
    private String status;
    private String createdAt;
    private List<String> actions;
	public AdminDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminDto(String userId,
			@NotEmpty(message = "This field required an Email id to be registered as an Admin") @Email(message = "Email address should be valid ") String userName,
			@NotEmpty(message = "Password can not be null ") String password, String userRole, String status,
			String createdAt, List<String> actions) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.userRole = userRole;
		this.status = status;
		this.createdAt = createdAt;
		this.actions = actions;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public List<String> getActions() {
		return actions;
	}
	public void setActions(List<String> actions) {
		this.actions = actions;
	}
    
    
}
