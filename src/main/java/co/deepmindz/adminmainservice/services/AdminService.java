package co.deepmindz.adminmainservice.services;

import co.deepmindz.adminmainservice.dto.AdminDto;
import co.deepmindz.adminmainservice.dto.LoginRequestDto;
import co.deepmindz.adminmainservice.dto.UpdateAdminDto;
import co.deepmindz.adminmainservice.models.Admin;
import jakarta.validation.Valid;

public interface AdminService {
	LoginRequestDto loginAdmin(LoginRequestDto loginAdmin);

	AdminDto createAdmin(AdminDto admin);

	AdminDto getAdminByUsername(String username);

	Admin updateAdminUser(@Valid UpdateAdminDto dto, AdminDto user);

	AdminDto getCoordinatorByLinkedZoneID(String linkedZoneId);
}
