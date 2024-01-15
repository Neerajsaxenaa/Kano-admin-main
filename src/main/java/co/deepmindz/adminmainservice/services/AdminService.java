package co.deepmindz.adminmainservice.services;

import java.util.List;

<<<<<<< HEAD
=======
import co.deepmindz.adminmainservice.dto.AdminChangePasswordDto;
>>>>>>> branch 'main' of https://github.com/SS-Whitelabel/ss-admin-main-service.git
import co.deepmindz.adminmainservice.dto.AdminDto;
import co.deepmindz.adminmainservice.dto.AdminResponseDto;
import co.deepmindz.adminmainservice.dto.UpdateAdminDto;
import co.deepmindz.adminmainservice.models.Admin;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.CordinatorIds;
import jakarta.validation.Valid;

public interface AdminService {

	AdminDto createAdmin(AdminDto admin);

	AdminDto getAdminByUsername(String username);

<<<<<<< HEAD
	Admin updateAdminUser(@Valid UpdateAdminDto dto ,AdminDto user);

	List<Admin> getMobileNoByCordinatorIds(CordinatorIds cordinatorIds);
=======
	AdminDto getCoordinatorByLinkedZoneID(String linkedZoneId);

	public Admin updateAdminUser(@Valid UpdateAdminDto dto, String userName);

	public List<AdminResponseDto> getAllAdminUsers();

	public String resetPassword(String userName, String password);

	public String changePassword(String userName, AdminChangePasswordDto dto);

>>>>>>> branch 'main' of https://github.com/SS-Whitelabel/ss-admin-main-service.git
}
