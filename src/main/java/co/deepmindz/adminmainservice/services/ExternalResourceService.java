package co.deepmindz.adminmainservice.services;

import java.util.List;

import co.deepmindz.adminmainservice.dto.ExternalReourceResponseDTO;
import co.deepmindz.adminmainservice.dto.ExternalResourceDTO;
import co.deepmindz.adminmainservice.dto.UserBaseAvailabilityDTO;

public interface ExternalResourceService {
    String isResourceExist(String resource);

    ExternalResourceDTO createResource(ExternalResourceDTO externalResourceDTO);

    boolean isAvailbilityFound();

    UserBaseAvailabilityDTO createUserBaseAvailability(UserBaseAvailabilityDTO userBaseAvailabilityDTO);
    
    List<ExternalReourceResponseDTO> getAllExternalResource();
}
