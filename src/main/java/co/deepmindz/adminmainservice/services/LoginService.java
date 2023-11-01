package co.deepmindz.adminmainservice.services;

import co.deepmindz.adminmainservice.dto.AdminDto;
import co.deepmindz.adminmainservice.dto.LoginRequestDto;

public interface LoginService {
    LoginRequestDto loginAdmin(LoginRequestDto loginAdmin);
}
