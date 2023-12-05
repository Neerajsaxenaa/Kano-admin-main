package co.deepmindz.adminmainservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.deepmindz.adminmainservice.dto.ConfigManagementRequestDto;
import co.deepmindz.adminmainservice.models.ConfigurationManagement;
import co.deepmindz.adminmainservice.services.ConfigurationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin-main/config-management")
public class ConfigManagementController {
	
	@Autowired
	private ConfigurationService configurationService;
	
	@PostMapping("/set-config-management")
	public ConfigurationManagement setConfigManagement( @RequestBody ConfigManagementRequestDto dto) {
		return  configurationService.setConfigManagement(dto);
	}

}
