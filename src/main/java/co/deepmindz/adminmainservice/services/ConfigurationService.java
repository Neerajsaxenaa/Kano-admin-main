package co.deepmindz.adminmainservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.ConfigurationDto;
import co.deepmindz.adminmainservice.models.Configuration;

@Service
public interface ConfigurationService {

	Configuration setConfigManagement(ConfigurationDto status);

	Configuration getCurrentConfig(ConfigurationDto dto);

	List<Configuration> getAllConfig();

}
