package co.deepmindz.adminmainservice.dto;

import jakarta.validation.constraints.NotEmpty;

public class ThemeRequestDto {

	@NotEmpty(message = "Provide valid Literal ID")
	private String literal_id;

//	@NotEmpty(message = "Provide valid Literal")
//	private String literal;

	@NotEmpty(message = "Provide color")
	private String color;

	@NotEmpty(message = "Provide valid Image URL")
	private String imageURL;
}
