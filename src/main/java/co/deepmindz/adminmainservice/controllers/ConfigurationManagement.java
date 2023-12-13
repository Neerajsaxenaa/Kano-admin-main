package co.deepmindz.adminmainservice.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.deepmindz.adminmainservice.dto.LoginModeStatusDto;
import co.deepmindz.adminmainservice.models.Resources;
import co.deepmindz.adminmainservice.resources.CustomHttpResponse;
import co.deepmindz.adminmainservice.services.ConfigurationService;
import co.deepmindz.adminmainservice.services.LanguageService;
import co.deepmindz.adminmainservice.services.LoginModeService;
import co.deepmindz.adminmainservice.services.ResourceService;
import co.deepmindz.adminmainservice.services.ThemeService;
import co.deepmindz.adminmainservice.utils.CustomDataTypes;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.themes;
import co.deepmindz.adminmainservice.utils.Templates;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:app.properties")
@RestController
@RequestMapping("/admin-main/config")
public class ConfigurationManagement {

	@Autowired
	ConfigurationService configurationService;

	@Autowired
	LoginModeService loginModeService;

	@Autowired
	LanguageService languageService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	ThemeService themeService;

	@Value("${ISS_STATE_LIMIT}")
	private String iss_state_limit;

	@Value("${ISS_ZONAL_LIMIT}")
	private String iss_zonal_limit;

	@Value("${ISS_LGA_LIMIT}")
	private String iss_lga_limit;

	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;

	public ConfigurationManagement(RestTemplate restTemplate, ObjectMapper objectMapper) {
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
	}

//    @PostMapping("/add-service")
//    public ResponseEntity<Object> addService(@Valid @RequestBody co.deepmindz.adminmainservice.models.ConfigurationManagement configurationManagement) {
//        configurationService.addService(configurationManagement);
//        return CustomHttpResponse.responseBuilder("Service Created At Configuration Level", HttpStatus.CREATED, "configurationManagement");
//    }

	public Map<String, String> getVisitModeConfigurations() {
		RequestEntity<Void> request = RequestEntity
				.get(Templates.ALLSERVICES.visit_service.toString() + "/visits/flw/get-visit-configuration")
				.accept(MediaType.APPLICATION_JSON).build();
		return restTemplate.exchange(request, Templates.responseTypeForRestAPICall).getBody();
	}

	@GetMapping("/get-configurations")
	public ResponseEntity<Object> primary() throws JsonProcessingException {
		Map<String, Object> response = new LinkedHashMap<>();
		LoginModeStatusDto currentLoginModeStatus = loginModeService.getCurrentConfig();
		List<themes> currentThemesSetting = themeService.getCurrentThemeSetting();
		Map<String, String> visitModeConfiguration = getVisitModeConfigurations();
		List<CustomDataTypes.valueObj> result = languageService.getSupportedLanguageList();
		Map<String, String> appStatics = getStringStringMap();
		response.put("currentLoginModeStatus", currentLoginModeStatus);
		new CustomDataTypes.memberLimitObj(iss_state_limit, iss_zonal_limit, iss_lga_limit);
		response.put("rolesVisitConfig",
				new CustomDataTypes.memberLimitObj(iss_state_limit, iss_zonal_limit, iss_lga_limit));
		response.put("currentTheme", currentThemesSetting);
		response.put("supportedLanguage", result);
		response.put("appStatics", appStatics);

		if (visitModeConfiguration.get("message").contains(Templates.VISITTYPES.Team_Visit.name()))
			response.put("teamsVisitConfigured", true);
		else {
			response.put("teamsVisitConfigured", false);
		}
		String serviceUrl = "lb://ss-visit-service/visits/monthly-planner/get-weekend";

		String responseString = restTemplate.getForObject(serviceUrl, String.class);
		List<Object> objects = List.of(responseString);
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> monthlyPlannerConf = objectMapper.readValue(responseString,
				new TypeReference<Map<String, Object>>() {
				});
		response.put("monthly_planner_conf", monthlyPlannerConf.get("data"));

		return CustomHttpResponse.responseBuilder("Configured Status", HttpStatus.ACCEPTED, response);
	}

	private Map<String, String> getStringStringMap() {
		List<Resources> items = resourceService.getResources();

		Map<String, String> appStatics = new HashMap<>();
		for (Resources resource : items) {
			String type = resource.getType();
			String url = resource.getUrl();

			// Check if the type and url are not null or empty
			if (type != null && !type.isEmpty() && url != null && !url.isEmpty()) {
				appStatics.put(type, url);
			}
		}
		return appStatics;
	}

	@PostMapping("/clean-literals")
	public ResponseEntity<Object> cleanLiterals() {
		languageService.cleanAllLanguageLiterals();
		return CustomHttpResponse.responseBuilder("Literals and Languages has been cleared", HttpStatus.OK,
				"/clean-literals");
	}

	@PostMapping("/reset-login-mode")
	public ResponseEntity<Object> resetLoginMode() {
		loginModeService.resetLoginMode();
		return CustomHttpResponse.responseBuilder("LoginMode has been reset", HttpStatus.OK, "/reset-login-mode");
	}

//    @DeleteMapping("/clean-literals")
//    public ResponseEntity<Object> cleanLiterals() {
//        languageService.cleanAllLanguageLiterals();
//        return CustomHttpResponse.responseBuilder("Literals and Languages has been cleared", HttpStatus.OK, "/clean-literals");
//    }

//    @DeleteMapping("/reset-login-mode")
//    public ResponseEntity<Object> resetLoginMode() {
//        loginModeService.resetLoginMode();
//        return CustomHttpResponse.responseBuilder("LoginMode has been reset", HttpStatus.OK, "/reset-login-mode");
//    }
}
