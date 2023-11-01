package co.deepmindz.adminmainservice.models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class JobAids {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String jobid;

	@NotNull
	@NotEmpty
	private String jobName;

	private String role_id;

	private Timestamp read_duration;

	private String profile_img;

	private String content_type;

	//@Column(length = 10000)
	private String content;

}
