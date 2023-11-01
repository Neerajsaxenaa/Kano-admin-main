package co.deepmindz.adminmainservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Themes {

	@Id
	@Column(unique = true, updatable = false)
	private String literal_id;
	
	@NotEmpty
	@NotNull
	private String description;

	@NotEmpty
	@NotNull
	private String color;

	private String imageURL;
}
