package co.deepmindz.adminmainservice.services;

import co.deepmindz.adminmainservice.dto.AdminDto;
import co.deepmindz.adminmainservice.dto.LoginRequestDto;


public interface AdminService {
    LoginRequestDto loginAdmin(LoginRequestDto loginAdmin);

    AdminDto createAdmin(AdminDto admin);
    AdminDto getAdminById(String adminId);
}
