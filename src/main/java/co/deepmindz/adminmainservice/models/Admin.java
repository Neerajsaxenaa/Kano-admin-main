package co.deepmindz.adminmainservice.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Admin {
	@Id
	private String userId;

	private String userName;
	
	private String name;
	
	private String password;
	
	private String role;
	
	private String status;
	
	private String createdAt;
	
	@Column(nullable = true)
	private List<String> actions;

}
