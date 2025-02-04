package co.deepmindz.adminmainservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConfigurationDto {

	private String id;

	@NotNull
	private String configuration;

	@NotNull
	private boolean locked;

}
