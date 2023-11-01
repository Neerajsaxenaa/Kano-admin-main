package co.deepmindz.adminmainservice.services.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.ExternalReourceResponseDTO;
import co.deepmindz.adminmainservice.dto.ExternalResourceDTO;
import co.deepmindz.adminmainservice.dto.UserBaseAvailabilityDTO;
import co.deepmindz.adminmainservice.models.ExternalResource;
import co.deepmindz.adminmainservice.models.UserBaseAvailability;
import co.deepmindz.adminmainservice.repository.ExternalResourceRepo;
import co.deepmindz.adminmainservice.repository.UserBaseAvailabilityRepo;
import co.deepmindz.adminmainservice.services.ExternalResourceService;

@Service
public class ExternalResourceImpl implements ExternalResourceService {
	@Autowired
	ExternalResourceRepo externalResourceRepo;

	@Autowired
	UserBaseAvailabilityRepo userBaseAvailabilityRepo;

	public String generateRandomUserId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	@Override
	public String isResourceExist(String resource) {
		String resources = externalResourceRepo.getResourceByType(resource);
		return resources;
	}

	@Override
	public ExternalResourceDTO createResource(ExternalResourceDTO externalResourceDTO) {
		ExternalResource externalResource = new ExternalResource();
		externalResource.set_id(generateRandomUserId());
		externalResource.setResource(externalResourceDTO.getResource());
		externalResource.setType(externalResourceDTO.getType());
		externalResource.setDescription(externalResourceDTO.getDescription());
		externalResource.setMaintained_by(externalResourceDTO.getMaintained_by());

		// Set the created_at field with the current UTC timestamp
		Instant currentUTC = Instant.now();
		externalResource.setCreated_at(currentUTC.toString());
		ExternalResource externalResourceResponse = externalResourceRepo.save(externalResource);
		return externalResourceDTO;
	}

	@Override
	public boolean isAvailbilityFound() {
		List<UserBaseAvailability> availabilityList = userBaseAvailabilityRepo.getAvailability();
		return availabilityList != null && !availabilityList.isEmpty();
	}

    @Override
    public UserBaseAvailabilityDTO createUserBaseAvailability(UserBaseAvailabilityDTO userBaseAvailabilityDTO) {
        UserBaseAvailability userBaseAvailability = new UserBaseAvailability();
        String resources = "";
        if (userBaseAvailabilityDTO.isExternal_availability()) {
            resources = externalResourceRepo.getResourceByType("User-base");
        }
        System.out.println(resources + "resources");
        if (resources != null) {
            userBaseAvailability.set_id(generateRandomUserId());
            userBaseAvailability.setExternal_availability(userBaseAvailabilityDTO.isExternal_availability());
            userBaseAvailability.setProvided_url(resources);
            Instant currentUTC = Instant.now();
            userBaseAvailability.setCreated_at(currentUTC.toString());
            userBaseAvailabilityRepo.save(userBaseAvailability);
        } else {
            userBaseAvailabilityDTO.setMessage("Please create HRM resource first");
            userBaseAvailabilityDTO.setMessage_code("0000");
            return userBaseAvailabilityDTO;
        }
        return userBaseAvailabilityDTO;
    }

	@Override
	public List<ExternalReourceResponseDTO> getAllExternalResource() {
		List<ExternalResource> allResource = externalResourceRepo.findAll();
		if (allResource.isEmpty() || allResource.size() == 0)
			return null;
		List<ExternalReourceResponseDTO> response = new ArrayList<>();
		for (ExternalResource resource : allResource) {
			response.add(
					new ExternalReourceResponseDTO(resource.get_id(), resource.getType(), resource.getDescription(),
							resource.getResource(), resource.getMaintained_by(), resource.getCreated_at()));
		}
		return response;
	}

}
