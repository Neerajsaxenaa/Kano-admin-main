package co.deepmindz.adminmainservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.ConfigManagementRequestDto;
import co.deepmindz.adminmainservice.models.ConfigurationManagement;
import co.deepmindz.adminmainservice.repository.ConfigurationRepository;
import co.deepmindz.adminmainservice.services.ConfigurationService;
import co.deepmindz.adminmainservice.utils.configUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfigurationServiceImp implements ConfigurationService {
	
	@Autowired
	private ConfigurationRepository configRepository;
	
	@Autowired
	private configUtil configUtil;
	
	
	@Override
	public ConfigurationManagement setConfigManagement(ConfigManagementRequestDto dto) {
		ConfigurationManagement configurationManagement =  configUtil.mapDtoToEntity(dto);
		
		return configRepository.save(configurationManagement);
//		return configRepository.save(configUtil.mapDtoToEntity(dto));
		
	}

	
}
