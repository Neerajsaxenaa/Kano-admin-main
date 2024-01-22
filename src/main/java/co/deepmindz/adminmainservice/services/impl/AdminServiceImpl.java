package co.deepmindz.adminmainservice.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.deepmindz.adminmainservice.dto.AdminChangePasswordDto;
import co.deepmindz.adminmainservice.dto.AdminDto;
import co.deepmindz.adminmainservice.dto.AdminResponseDto;
import co.deepmindz.adminmainservice.dto.UpdateAdminDto;
import co.deepmindz.adminmainservice.dto.ZoneListsDto;
import co.deepmindz.adminmainservice.exception.ResourceAlreadyExist;
import co.deepmindz.adminmainservice.exception.ResourceNotFoundException;
import co.deepmindz.adminmainservice.models.Admin;
import co.deepmindz.adminmainservice.repository.AdminRepository;
import co.deepmindz.adminmainservice.services.AdminService;
import co.deepmindz.adminmainservice.utils.AdminUtil;
import co.deepmindz.adminmainservice.utils.Templates;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AdminUtil adminUtil;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public AdminDto createAdmin(AdminDto adminDto) {
		Optional<Admin> optionalAdmin = adminRepository.findByUserName(adminDto.getUserName());
		if (optionalAdmin.isPresent())
			throw new ResourceAlreadyExist("Admin User Already Exist");

		Admin admin = adminUtil.mapToAdmin(adminDto);
		Admin savedAdmin = adminRepository.save(admin);
		AdminDto adminSavedDto = adminUtil.mapToAdminDto(savedAdmin);
		return adminSavedDto;
	}

	@Override
	public AdminDto getAdminByUsername(String userName) {
		Admin admin = adminRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userName));
		return adminUtil.mapToAdminDto(admin);
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
	public ArrayList<AdminDto> getCoordinatorByLinkedZoneID(String linkedZoneId) {
		ArrayList<AdminDto> response = new ArrayList<>();
		Admin adminUser = adminRepository.getAdminUserByLinkedZoneId(linkedZoneId);
		if (adminUser == null)
			throw new ResourceNotFoundException("User", "Id", linkedZoneId);
		AdminDto dto = adminUtil.mapToAdminDto(adminUser);
		if (dto != null)
			response.add(dto);
		return response;
	}

	@Override
	public List<Admin> getPhoneNumbersOfAdmins(List<String> admins) {
		return adminRepository.findAllById(admins);
	}

	public List<AdminResponseDto> getAllAdminUsers() {
		List<Admin> allAdmins = adminRepository.findAll(Sort.by(Sort.Direction.ASC, "userName"));
		List<String> alllinkedzones = allAdmins.stream().filter(a -> a.getLinked_zone() != null)
				.collect(Collectors.toList()).stream().map(a -> a.getLinked_zone()).collect(Collectors.toList());
		ResponseEntity<List<ZoneListsDto>> rateResponse = null;
		if (alllinkedzones != null && !alllinkedzones.isEmpty()) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> httpEntity = new HttpEntity<Object>(alllinkedzones, headers);
			String url = Templates.ALLSERVICES.admin_org.toString() + "/organization/zone-list/get-all-zonelist";
			try {
				rateResponse = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
						new ParameterizedTypeReference<List<ZoneListsDto>>() {
						});
			} catch (Exception e) {
				System.out.println(url + "Not working, ADMIN_ORG Service not responding");
				e.printStackTrace();
			}
		}

		List<ZoneListsDto> dtos = rateResponse.getBody() != null ? rateResponse.getBody() : new ArrayList<>();
		Map<String, ZoneListsDto> idWithZoneMap = new HashMap<>();
		dtos.stream().map(a -> idWithZoneMap.put(a.get_id(), a)).collect(Collectors.toList());

		List<AdminResponseDto> response = new ArrayList<>();
		for (Admin admin : allAdmins)
			response.add(new AdminResponseDto(admin.getUserId(), admin.getUserName(), admin.getEmail(),
					idWithZoneMap.get(admin.getLinked_zone()) != null
							? idWithZoneMap.get(admin.getLinked_zone()).getName()
							: null,
					admin.getPhone_number(), admin.getRole(), admin.isActive()));
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

	@Override
	public String blockAndUnblockAdmin(String id) {
		Admin findAdmin = adminRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ADMIN", id, id));
		String message = "";
		if (findAdmin.isActive())
			findAdmin.setActive(false);
		else
			findAdmin.setActive(true);
		Admin savedAdmin = adminRepository.save(findAdmin);
		message = "admin updated sucessfully";
		if (savedAdmin == null)
			message = "Active/Inactive admin user failed";
		return message;
	}
}
