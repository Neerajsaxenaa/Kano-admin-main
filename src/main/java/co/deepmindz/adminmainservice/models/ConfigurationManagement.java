package co.deepmindz.adminmainservice.models;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConfigurationManagement {

	@Id
	@UuidGenerator
	private String id;
	private String configuration;

	@NotNull
	private boolean locked;

}
