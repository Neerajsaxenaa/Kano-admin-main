package co.deepmindz.adminmainservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UpdateAdminDto {

	private String email;

	private String phone_number;

}
