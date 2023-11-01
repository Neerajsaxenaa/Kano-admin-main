package co.deepmindz.adminmainservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@Data
@Entity
//@NoArgsConstructor
//@AllArgsConstructor
public class Admin {
    @Id
    private String userId;
    private String userName;
    private String password;
    private String role;
    private String status;
    private String createdAt;
    @Column(nullable = true)
    private List<String> actions;
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(String userId, String userName, String password, String role, String status, String createdAt,
			List<String> actions) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.role = role;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
