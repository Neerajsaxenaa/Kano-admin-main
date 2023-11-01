package co.deepmindz.adminmainservice.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.LoginModeStatusDto;
import co.deepmindz.adminmainservice.models.LoginMode;

@Service
public class LoginModeUtil {

	public enum MODES {
		Two_FA, USER_CREDENTIALS
	};

	public LoginMode mapToLoginModeEntity(LoginModeStatusDto config) {
		String configuration = config.getCountryCode() + "," + config.getDigitsLength();
		LoginMode loginMode = new LoginMode();
		loginMode.setStatus(true);
		if (config.getIsTwoFA()) {
			loginMode.setLoginmode("2FA");
		} else {
			loginMode.setLoginmode(MODES.USER_CREDENTIALS.name());
		}
		loginMode.setConfiguration(configuration);

		return loginMode;
	}

	public LoginModeStatusDto mapToRequestObject(List<LoginMode> modes) {

		LoginMode twoFA = new LoginMode();
		LoginMode credMode = new LoginMode();
		LoginModeStatusDto resposeData = new LoginModeStatusDto();
		for (LoginMode mode : modes) {
			if (mode.getLoginmode().equals(MODES.USER_CREDENTIALS.name()))
				credMode = mode;
			else
				twoFA = mode;
		}

		resposeData.setIsTwoFA(twoFA.isStatus());
		resposeData.setUserCredentials(credMode.isStatus());
		if (twoFA.isStatus()) {
			List<String> token = new ArrayList<>();
			if (twoFA.getConfiguration() != null && !twoFA.getConfiguration().isEmpty()) {
				token = List.of(twoFA.getConfiguration().split(","));
				resposeData.setCountryCode(token.get(0));
				resposeData.setDigitsLength(token.size() > 1 ? token.get(1) : "");
			}
		}
		return resposeData;
	}

}
