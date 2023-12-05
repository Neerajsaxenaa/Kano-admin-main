package co.deepmindz.adminmainservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BrandImagesResponseDto {

	private String login_screen;
	private String splash_screen;

}
