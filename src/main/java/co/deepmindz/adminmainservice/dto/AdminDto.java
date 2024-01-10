package co.deepmindz.adminmainservice.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminDto {
	private String userId;

	@NotEmpty(message = "This field required an Email id to be registered as an Admin")
	@NotNull
	private String userName;

	private String email;

	private String linked_zone;

	private String phone_number;

	@NotEmpty(message = "Password can not be null ")
	private String password;

	private String userRole;

	private String status;

	private String createdAt;

	private List<String> actions;

}
