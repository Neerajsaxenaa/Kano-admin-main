package co.deepmindz.adminmainservice.dto;

import java.util.List;

import co.deepmindz.adminmainservice.models.Configuration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AllConfigurationResponseDto {

	private boolean teamsVisit;
	private List<Configuration> freezeConfiguration;

}
