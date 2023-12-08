package co.deepmindz.adminmainservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.deepmindz.adminmainservice.dto.ConfigManagementRequestDto;
import co.deepmindz.adminmainservice.models.ConfigurationManagement;
import co.deepmindz.adminmainservice.resources.CustomHttpResponse;
import co.deepmindz.adminmainservice.services.ConfigurationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin-main/config-management")
public class ConfigManagementController {

	@Autowired
	private ConfigurationService configurationService;

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
		return CustomHttpResponse.responseBuilder("All Configuration ", HttpStatus.OK,
				configurationService.getAllConfig());
	}
}
