package co.deepmindz.adminmainservice.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.LoginModeStatusDto;
import co.deepmindz.adminmainservice.models.LoginMode;
import co.deepmindz.adminmainservice.repository.LoginModeRepository;
import co.deepmindz.adminmainservice.services.LoginModeService;
import co.deepmindz.adminmainservice.utils.LoginModeUtil;

@Service
public class LoginModeServiceImpl implements LoginModeService {

	@Autowired
	LoginModeUtil loginModeUtil;

	@Autowired
	LoginModeRepository loginModeRepository;
	
	@Override
	public void updateLoginMode(LoginModeStatusDto config) {
		LoginMode currMode = loginModeUtil.mapToLoginModeEntity(config);
		if(currMode.getLoginmode().equals("2FA"))
			loginModeRepository.updateLoginModeStatus("Two_FA", currMode.getConfiguration());
		else {
			loginModeRepository.updateLoginModeStatus("USER_CREDENTIALS", currMode.getConfiguration());			
		}
	}

	@Override
	public LoginModeStatusDto getCurrentConfig() {
		List<LoginMode> modeDetails = loginModeRepository.findAll();
		if( modeDetails.isEmpty() )
			return new LoginModeStatusDto(false, false, "Please Configure LoginMode","");
		else
			return loginModeUtil.mapToRequestObject(modeDetails);
	}
	
	@Override
	public void resetLoginMode() {
		loginModeRepository.resetLoginMode("Two_FA", "");
		loginModeRepository.resetLoginMode("USER_CREDENTIALS", "");
	}
}
