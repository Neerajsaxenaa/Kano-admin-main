package co.deepmindz.adminmainservice.dto;

import co.deepmindz.adminmainservice.utils.CustomDataTypes.themes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangeThemesRequestDto {

	private themes[] themes;
}
