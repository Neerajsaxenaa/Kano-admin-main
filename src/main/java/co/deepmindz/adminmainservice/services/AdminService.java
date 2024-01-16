package co.deepmindz.adminmainservice.services;

import java.util.List;

import co.deepmindz.adminmainservice.dto.AdminChangePasswordDto;
import co.deepmindz.adminmainservice.dto.AdminDto;
import co.deepmindz.adminmainservice.dto.AdminResponseDto;
import co.deepmindz.adminmainservice.dto.UpdateAdminDto;
import co.deepmindz.adminmainservice.models.Admin;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.CordinatorIds;
import jakarta.validation.Valid;

public interface AdminService {

	AdminDto createAdmin(AdminDto admin);

	AdminDto getAdminByUsername(String username);

	List<Admin> getMobileNoByCordinatorIds(CordinatorIds cordinatorIds);

	AdminDto getCoordinatorByLinkedZoneID(String linkedZoneId);

	public Admin updateAdminUser(@Valid UpdateAdminDto dto, String userName);

	public List<AdminResponseDto> getAllAdminUsers();

	public String resetPassword(String userName, String password);

	public String changePassword(String userName, AdminChangePasswordDto dto);

	public String blockAndUnblockAdmin(String id);
}
