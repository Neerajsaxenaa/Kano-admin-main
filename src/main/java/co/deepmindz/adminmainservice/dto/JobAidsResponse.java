package co.deepmindz.adminmainservice.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobAidsResponse {
	
	private String jobid;
	private String jobName;
	private String role_id;
	private Timestamp read_duration;
	private String profile_img;
	private String content_type;

	private String content;
	

}
