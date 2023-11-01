package co.deepmindz.adminmainservice.services.impl;
import org.modelmapper.ModelMapper;

import co.deepmindz.adminmainservice.dto.ConfigurationManagementDto;

import co.deepmindz.adminmainservice.services.ConfigurationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConfigurationServiceImp implements ConfigurationService {

//    @Autowired
//    ConfigurationRepository configurationRepository;

//    @Autowired
//    private ModelMapper modelMapper;
//    private ModelMapper modelMapper = new ModelMapper();

//    @Override
//    public ConfigurationManagement addService(ConfigurationManagement configurationManagement) {
//        ConfigurationManagement entity = new ConfigurationManagement();
//        // Copy other properties
//        return configurationRepository.save(configurationManagement);
//    }

    @Override
    public List<ConfigurationManagementDto> getActiveServiceStatus() {
        return null;
    }

//    @Override
//    public List<ConfigurationManagementDto> getActiveServiceStatus() {
//        Iterable<ConfigurationManagement> configurationIterable = configurationRepository.findAll();
//        if (configurationIterable == null) {
//            // Handle the case where the repository returns null (e.g., log an error or throw an exception).
//            throw new ResourceNotFoundException("Configuration data is null.", "Server is not configured", "");
//        }
//
//        List<ConfigurationManagement> configurationList = new ArrayList<>();
//        // Convert the Iterable to a List
//        configurationIterable.forEach(configurationList::add);
//
//        return configurationList.stream()
//                .map(AutoConfigManagementMapper.MAPPER::mapToConfigurationManagementDto)
//                .collect(Collectors.toList());
//    }
}
