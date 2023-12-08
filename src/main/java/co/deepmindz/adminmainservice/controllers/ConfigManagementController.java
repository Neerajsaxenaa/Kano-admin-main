package co.deepmindz.adminmainservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.deepmindz.adminmainservice.dto.ConfigManagementRequestDto;
import co.deepmindz.adminmainservice.resources.CustomHttpResponse;
import co.deepmindz.adminmainservice.services.ConfigurationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin-main/config-management")
public class ConfigManagementController {

	@Autowired
	private ConfigurationService configurationService;

	@PostMapping("/set-config-management")
	public ResponseEntity<Object> setConfigManagement(@Valid @RequestBody ConfigManagementRequestDto dto) {
		return CustomHttpResponse.responseBuilder("Configuration has been locked", HttpStatus.OK,
				configurationService.setConfigManagement(dto));
	}

}
