package co.deepmindz.adminmainservice.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.ConfigurationDto;
import co.deepmindz.adminmainservice.models.Configuration;
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
	public Configuration setConfigManagement(ConfigurationDto dto) {
		Configuration configurationManagement = null;
		Configuration configFoundInDB = getCurrentConfig(dto);
		configurationManagement = configUtil.mapDtoToEntity(dto);
		if (configFoundInDB != null)
			configurationManagement.setId(configFoundInDB.getId());
		return configRepository.save(configurationManagement);
	}

	@Override
	public Configuration getCurrentConfig(ConfigurationDto dto) {
		Configuration currentStatusOfParticularConfig = configRepository
				.findCurrentStatusOfConfig(dto.getConfiguration());
		return currentStatusOfParticularConfig;
	}

	@Override
	public List<Configuration> getAllConfig() {
		return configRepository.findAll();
	}
}
