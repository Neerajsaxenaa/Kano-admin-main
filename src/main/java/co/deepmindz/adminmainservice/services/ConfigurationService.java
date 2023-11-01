package co.deepmindz.adminmainservice.services;

import co.deepmindz.adminmainservice.dto.ConfigurationManagementDto;
//import co.deepmindz.adminmainservice.models.ConfigurationManagement;

import java.util.List;

public interface ConfigurationService {
//    public ConfigurationManagement addService(ConfigurationManagement configurationManagement);
    public List<ConfigurationManagementDto> getActiveServiceStatus();


}
