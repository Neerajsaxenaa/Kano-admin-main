package co.deepmindz.adminmainservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.ConfigManagementRequestDto;
import co.deepmindz.adminmainservice.models.ConfigurationManagement;

@Service
public interface ConfigurationService {

	ConfigurationManagement setConfigManagement(ConfigManagementRequestDto status);

	ConfigurationManagement getConfig(ConfigManagementRequestDto dto);

	List<ConfigurationManagement> getAllConfig();

}
