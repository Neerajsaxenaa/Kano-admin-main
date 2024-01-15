package co.deepmindz.adminmainservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminResponseDto {

	private String user_id;

	private String user_name;

	private String email;

	private String linked_zone;

	private String phone_number;

	private String user_role;

	private boolean active;
}
