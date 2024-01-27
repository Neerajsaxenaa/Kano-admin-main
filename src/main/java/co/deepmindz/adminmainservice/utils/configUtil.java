package co.deepmindz.adminmainservice.utils;

import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.ConfigurationDto;
import co.deepmindz.adminmainservice.models.Configuration;

@Service
public class configUtil {

	public Configuration mapDtoToEntity(ConfigurationDto dto) {
		Configuration configurationManagement = new Configuration();
		configurationManagement.setConfiguration(dto.getConfiguration());
		configurationManagement.setLocked(dto.isLocked());
		return configurationManagement;
	}
}
