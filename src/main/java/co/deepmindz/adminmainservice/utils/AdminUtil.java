package co.deepmindz.adminmainservice.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.AdminDto;
import co.deepmindz.adminmainservice.dto.UpdateAdminDto;
import co.deepmindz.adminmainservice.models.Admin;
import jakarta.validation.Valid;

@Service
public class AdminUtil {

	public Admin mapDtoToEntity(@Valid UpdateAdminDto dto, AdminDto user) {
		return new Admin(user.getUserId(), user.getUserName(), dto.getEmail(), user.getLinked_zone(),
				dto.getPhone_number(), user.getPassword(), user.getUserRole(), user.isActive(), user.getCreatedAt(),
				user.getActions());
	}

	public AdminDto mapToAdminDto(Admin admin) {
		if (admin == null) {
			return null;
		}
		AdminDto adminDto = new AdminDto();
		adminDto.setUserRole(admin.getRole());
		List<String> list = admin.getActions();
		if (list != null) {
			adminDto.setActions(new ArrayList<String>(list));
		}
		adminDto.setActive(admin.isActive());
		adminDto.setCreatedAt(admin.getCreatedAt());
		adminDto.setEmail(admin.getEmail());
		adminDto.setLinked_zone(admin.getLinked_zone());
		adminDto.setPassword(admin.getPassword());
		adminDto.setPhone_number(admin.getPhone_number());
		adminDto.setUserId(admin.getUserId());
		adminDto.setUserName(admin.getUserName());
		return adminDto;
	}

	public Admin mapToAdmin(AdminDto adminDto) {
		if (adminDto == null) {
			return null;
		}
		Admin admin = new Admin();
		List<String> list = adminDto.getActions();
		if (list != null) {
			admin.setActions(new ArrayList<String>(list));
		}
		admin.setRole(adminDto.getUserRole());
		admin.setActive(adminDto.isActive());
		admin.setCreatedAt(adminDto.getCreatedAt());
		admin.setEmail(adminDto.getEmail());
		admin.setLinked_zone(adminDto.getLinked_zone());
		admin.setPassword(adminDto.getPassword());
		admin.setPhone_number(adminDto.getPhone_number());
		admin.setUserId(adminDto.getUserId());
		admin.setUserName(adminDto.getUserName());
		return admin;
	}
}
