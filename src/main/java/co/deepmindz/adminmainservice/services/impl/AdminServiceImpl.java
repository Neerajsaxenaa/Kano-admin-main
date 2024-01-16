package co.deepmindz.adminmainservice.services.impl;

import java.util.ArrayList;
//github.com/SS-Whitelabel/ss-admin-main-service.git
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.AdminChangePasswordDto;
import co.deepmindz.adminmainservice.dto.AdminDto;
import co.deepmindz.adminmainservice.dto.AdminResponseDto;
import co.deepmindz.adminmainservice.dto.UpdateAdminDto;
import co.deepmindz.adminmainservice.exception.ResourceAlreadyExist;
import co.deepmindz.adminmainservice.exception.ResourceNotFoundException;
import co.deepmindz.adminmainservice.mapper.AutoAdminMapper;
import co.deepmindz.adminmainservice.models.Admin;
import co.deepmindz.adminmainservice.repository.AdminRepository;
import co.deepmindz.adminmainservice.services.AdminService;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.CordinatorIds;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public String generateRandomUserId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	@Override
	public AdminDto createAdmin(AdminDto adminDto) {
		Optional<Admin> optionalAdmin = adminRepository.findByUserName(adminDto.getUserName());
		if (optionalAdmin.isPresent())
			throw new ResourceAlreadyExist("Admin User Already Exist");

		adminDto.setUserId(generateRandomUserId());
		Admin admin = AutoAdminMapper.MAPPER.mapToAdmin(adminDto);
		Admin savedAdmin = adminRepository.save(admin);
		AdminDto adminSavedDto = AutoAdminMapper.MAPPER.mapToAdminDto(savedAdmin);
		return adminSavedDto;
	}

	@Override
	public AdminDto getAdminByUsername(String userName) {
		Admin admin = adminRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userName));
		return AutoAdminMapper.MAPPER.mapToAdminDto(admin);
	}

	@Override
	public Admin updateAdminUser(UpdateAdminDto dto, String userName) {
		Admin admin = adminRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("admin", userName, userName));

		if (dto.getEmail() != null || !dto.getEmail().isEmpty() || !dto.getEmail().equals(admin.getEmail()))
			admin.setEmail(dto.getEmail());
		if (dto.getPhone_number() != null || !dto.getPhone_number().isEmpty()
				|| !dto.getPhone_number().equals(admin.getPhone_number()))
			admin.setPhone_number(dto.getPhone_number());

		Admin savedAdmin = adminRepository.save(admin);
		return savedAdmin;
	}

	@Override
	public AdminDto getCoordinatorByLinkedZoneID(String linkedZoneId) {
		Admin adminUser = adminRepository.getAdminUserByLinkedZoneId(linkedZoneId);
		if (adminUser == null)
			throw new ResourceNotFoundException("User", "Id", linkedZoneId);
		return AutoAdminMapper.MAPPER.mapToAdminDto(adminUser);
	}

	@Override
	public List<Admin> getPhoneNumbersOfAdmins(List<String> admins) {
		return adminRepository.findAllById(admins);
	}

	public List<AdminResponseDto> getAllAdminUsers() {
		List<Admin> allAdmins = adminRepository.findAll();
		List<AdminResponseDto> response = new ArrayList<>();
		for (Admin admin : allAdmins)
			response.add(new AdminResponseDto(admin.getUserId(), admin.getUserName(), admin.getEmail(),
					admin.getLinked_zone(), admin.getPhone_number(), admin.getRole(), admin.isActive()));
		return response;
	}

	@Override
	public String resetPassword(String userName, String password) {
		Admin admin = adminRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("admin", userName, userName));
		admin.setPassword(passwordEncoder.encode(password));
		Admin savedAdmin = adminRepository.save(admin);
		return savedAdmin.getUserName();
	}

	public String changePassword(String userName, AdminChangePasswordDto dto) {
		String response = "Password changed successfully";
		Admin admin = adminRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("admin", userName, userName));
		if (!BCrypt.checkpw(dto.getCurrentPassword(), admin.getPassword()))
			return "Password mismatch !! enter correct password";

		admin.setPassword(passwordEncoder.encode(dto.getNewPassword()));
		Admin saveAdmin = adminRepository.save(admin);
		if (saveAdmin == null)
			response = "Change Password failed";
		return response;
	}

}
