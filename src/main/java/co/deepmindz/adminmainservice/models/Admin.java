package co.deepmindz.adminmainservice.models;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;

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
<<<<<<< HEAD
	@Column(name = "user_id")
=======
	@UuidGenerator
>>>>>>> branch 'main' of https://github.com/SS-Whitelabel/ss-admin-main-service.git
	private String userId;

	private String userName;

	private String email;

	private String linked_zone;
<<<<<<< HEAD
	
	@Column(name = "phone_number")
=======

>>>>>>> branch 'main' of https://github.com/SS-Whitelabel/ss-admin-main-service.git
	private String phone_number;

	private String password;

	private String role;

	private boolean active;

	private String createdAt;

	@Column(nullable = true)
	private List<String> actions;

}
