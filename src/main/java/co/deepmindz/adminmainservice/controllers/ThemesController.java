package co.deepmindz.adminmainservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.deepmindz.adminmainservice.dto.ChangeThemesRequestDto;
import co.deepmindz.adminmainservice.resources.CustomHttpResponse;
import co.deepmindz.adminmainservice.services.ThemeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin-main/themes")
public class ThemesController {
	
	@Autowired
	ThemeService themeService;
	
	private Logger logger = LoggerFactory.getLogger(ThemesController.class);

	@GetMapping("/get-current-themes")
	public ResponseEntity<Object> getCurrentThemeSetting() {
		logger.info("ThemesController.class:getCurrentThemeSetting:current-theme-setting");
		return CustomHttpResponse.responseBuilder("Current Themes Setting", HttpStatus.OK,
				themeService.getCurrentThemeSetting());
	}
	
	@GetMapping("/get-themes-literals")
	public ResponseEntity<Object> getCurrentThemesLiterals(){
		logger.info("ThemesController.class:getCurrentThemesLiterals:themes-literals");
		return CustomHttpResponse.responseBuilder("All Themes literals", HttpStatus.OK,
				themeService.getAllThemesLiterals());
	}
	
	@PostMapping("/change-themes")
	public ResponseEntity<Object> changeThemes(@Valid @RequestBody ChangeThemesRequestDto changeThemeDto) {
		logger.info("ThemesController.class:changeThemes:change-theme", changeThemeDto);
		return CustomHttpResponse.responseBuilder("Themes has been updated", HttpStatus.OK,
		themeService.changeThemes(changeThemeDto));
	}
	
	@PostMapping("/add-theme-literals")
	public ResponseEntity<Object> addNewThemeLiteral(@Valid @RequestBody ChangeThemesRequestDto newThemesDtos) {
		logger.info("ThemesController.class:addNewThemeLiteral:add-newThemes", newThemesDtos);
		return CustomHttpResponse.responseBuilder("New Themes has been added", HttpStatus.OK,
		themeService.addNewThemes(newThemesDtos));
	}
}
