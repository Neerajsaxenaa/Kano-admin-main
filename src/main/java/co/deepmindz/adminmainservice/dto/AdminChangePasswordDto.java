package co.deepmindz.adminmainservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminChangePasswordDto {

	private String currentPassword;
	private String newPassword;
}
