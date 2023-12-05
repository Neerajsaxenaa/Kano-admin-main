package co.deepmindz.adminmainservice.utils;

import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.ConfigManagementRequestDto;
import co.deepmindz.adminmainservice.models.ConfigurationManagement;

@Service
public class configUtil {

	public ConfigurationManagement mapDtoToEntity(ConfigManagementRequestDto dto) {
		ConfigurationManagement configurationManagement = new ConfigurationManagement();
		configurationManagement.setServiceId(dto.getServiceId());
		configurationManagement.setSubService(dto.getSubService());
		configurationManagement.setOrgServiceStatus(dto.getOrgServiceStatus());
		return configurationManagement;
	}

}
