package co.deepmindz.adminmainservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobAidsRequestDto {
	
	private String jobName;
	private String role_id;
	private String profile_img;
	private String content_type;
	private String content;
	

}
