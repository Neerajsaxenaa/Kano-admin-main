package co.deepmindz.adminmainservice.services;

import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.LoginModeStatusDto;

@Service
public interface LoginModeService {

	public void updateLoginMode(LoginModeStatusDto config);
	
	public LoginModeStatusDto getCurrentConfig();
	
	public void resetLoginMode();
}
