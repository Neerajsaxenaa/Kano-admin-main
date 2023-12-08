package co.deepmindz.adminmainservice.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.ConfigManagementRequestDto;
import co.deepmindz.adminmainservice.models.ConfigurationManagement;
import co.deepmindz.adminmainservice.repository.ConfigurationRepository;
import co.deepmindz.adminmainservice.services.ConfigurationService;
import co.deepmindz.adminmainservice.utils.configUtil;
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
		ConfigurationManagement configurationManagement = null;
		ConfigurationManagement configFoundInDB = getConfig(dto);
		configurationManagement = configUtil.mapDtoToEntity(dto);
		if (configFoundInDB != null)
			configurationManagement.setId(configFoundInDB.getId());
		return configRepository.save(configurationManagement);
	}

	@Override
	public ConfigurationManagement getConfig(ConfigManagementRequestDto dto) {
		ConfigurationManagement findByService = configRepository.findByService(dto.getConfiguration());

		return findByService;

	}

	@Override
	public List<ConfigurationManagement> getAllConfig() {
		return  configRepository.findAll();
	}

}
