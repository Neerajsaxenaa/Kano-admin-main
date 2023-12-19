package co.deepmindz.adminmainservice.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import co.deepmindz.adminmainservice.dto.ConfigManagementRequestDto;
import co.deepmindz.adminmainservice.models.ConfigurationManagement;
import co.deepmindz.adminmainservice.resources.CustomHttpResponse;
import co.deepmindz.adminmainservice.services.ConfigurationService;
import co.deepmindz.adminmainservice.utils.Templates;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin-main/config-management")
public class ConfigManagementController {

	@Autowired
	private ConfigurationService configurationService;
	
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/set-configuration")
	public ResponseEntity<Object> setConfigManagement(@Valid @RequestBody ConfigManagementRequestDto dto) {
		return CustomHttpResponse.responseBuilder("Configuration has been locked", HttpStatus.OK,
				configurationService.setConfigManagement(dto));
	}

	@PostMapping("/get-configuration")
	public Object getConfiguration(@Valid @RequestBody ConfigManagementRequestDto dto) {
		ConfigurationManagement config = configurationService.getConfig(dto);
		if (config == null) {
			return CustomHttpResponse.responseBuilder("No Configution found", HttpStatus.OK, " ");
		}
		return CustomHttpResponse.responseBuilder("Configuration found", HttpStatus.FOUND, config);
	}

	@GetMapping("/get-all-configuration")
	public Object getConfiguration() {
		List<ConfigurationManagement> allFreezeConfiguration = configurationService.getAllConfig();
		Map<String,  Object> resObj = new HashMap<>();
		resObj.put("freeze_configuration", allFreezeConfiguration);
		Map<String, String> visitModeConfigurations = getVisitModeConfigurations();
		if (visitModeConfigurations.get("message").contains(Templates.VISITTYPES.Team_Visit.name()))
			resObj.put("teamsVisitConfigured", true);
		else {
			resObj.put("teamsVisitConfigured", false);
		}
		
		return CustomHttpResponse.responseBuilder("All Configuration ", HttpStatus.OK,
				resObj);
	}
	
//	@GetMapping("/get-team-mode")
	public Map<String, String> getVisitModeConfigurations() {
		RequestEntity<Void> request = RequestEntity
				.get(Templates.ALLSERVICES.visit_service.toString() + "/visits/flw/get-visit-configuration")
				.accept(MediaType.APPLICATION_JSON).build();
		return restTemplate.exchange(request, Templates.responseTypeForRestAPICall).getBody();
	}
	
}
