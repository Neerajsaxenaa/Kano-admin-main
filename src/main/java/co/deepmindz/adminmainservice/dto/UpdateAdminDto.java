package co.deepmindz.adminmainservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class UpdateAdminDto {
	
	private String email;
	
	private String password;

	@NotEmpty
	@NotNull
	private String phone_number;

}
