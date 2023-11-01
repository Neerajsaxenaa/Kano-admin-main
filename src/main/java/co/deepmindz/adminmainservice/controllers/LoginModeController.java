package co.deepmindz.adminmainservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.deepmindz.adminmainservice.dto.LoginModeStatusDto;
import co.deepmindz.adminmainservice.exception.ResourceAlreadyExist;
import co.deepmindz.adminmainservice.models.LoginMode;
import co.deepmindz.adminmainservice.repository.LoginModeRepository;
import co.deepmindz.adminmainservice.resources.CustomHttpResponse;
import co.deepmindz.adminmainservice.services.LoginModeService;

@RestController
@RequestMapping("/admin-main/login-mode")
public class LoginModeController {

	@Autowired
	LoginModeRepository loginModeRepository;

	@Autowired
	LoginModeService loginService;

	// will be called from Web Browser only ( 2FA/Credentails + Config ( countryCode
	// + Length) )
	@PostMapping("/update-status")
	public ResponseEntity<Object> setLoginMode(@RequestBody LoginModeStatusDto config) {
		List<LoginMode> allMode = loginModeRepository.findAll();
		if (allMode.get(0).isStatus() || allMode.get(1).isStatus()) {
			throw new ResourceAlreadyExist(
					"LoginMode configuration found : " + allMode.get(0).getLoginmode() + ", can't update");
		}
		loginService.updateLoginMode(config);
		return CustomHttpResponse.responseBuilder("LoginMode has been updated", HttpStatus.OK, config);
	}

	@GetMapping("/current-status")
	public ResponseEntity<Object> getLoginMode() {
		LoginModeStatusDto config = loginService.getCurrentConfig();
		return CustomHttpResponse.responseBuilder("Current LoginMode status:", HttpStatus.OK, config);
	}

	@GetMapping("/current-loginMode-status")
	public ResponseEntity<Object> getLoginModeForOtherServices() {
		LoginModeStatusDto config = loginService.getCurrentConfig();
		if (config.getIsTwoFA())
			return CustomHttpResponse.responseBuilder(config.getCountryCode() + "," + config.getDigitsLength(),
					HttpStatus.OK, "Two_FA");
		else if (config.isUserCredentials()) {
			return CustomHttpResponse.responseBuilder("", HttpStatus.OK, "USER_CREDENTIALS");
		} else {
			return CustomHttpResponse.responseBuilder("LoginMode is not Configured", HttpStatus.OK,
					"No Configuration found");
		}
	}
}
