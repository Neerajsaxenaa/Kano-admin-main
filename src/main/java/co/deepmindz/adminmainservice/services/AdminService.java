package co.deepmindz.adminmainservice.services;

import java.util.List;

import co.deepmindz.adminmainservice.dto.AdminDto;
import co.deepmindz.adminmainservice.dto.LoginRequestDto;
import co.deepmindz.adminmainservice.dto.UpdateAdminDto;
import co.deepmindz.adminmainservice.models.Admin;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.CordinatorIds;
import jakarta.validation.Valid;

public interface AdminService {
	LoginRequestDto loginAdmin(LoginRequestDto loginAdmin);

	AdminDto createAdmin(AdminDto admin);

	AdminDto userByUsername(String username);

	Admin updateAdminUser(@Valid UpdateAdminDto dto ,AdminDto user);

	List<Admin> getMobileNoByCordinatorIds(CordinatorIds cordinatorIds);
}
