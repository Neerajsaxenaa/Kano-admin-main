package co.deepmindz.adminmainservice.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import co.deepmindz.adminmainservice.dto.AdminChangePasswordDto;
import co.deepmindz.adminmainservice.dto.AdminDto;
import co.deepmindz.adminmainservice.dto.UpdateAdminDto;
import co.deepmindz.adminmainservice.models.Admin;
import co.deepmindz.adminmainservice.resources.CustomHttpResponse;
import co.deepmindz.adminmainservice.services.AdminService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin-main")
public class AdminUserController {
	
	@Autowired
	private AdminService adminService;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<Object> createAdminUser(@Valid @RequestBody AdminDto admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		AdminDto savedAdminDto = adminService.createAdmin(admin);
		Map<String, Object> response = new LinkedHashMap<>();
		response.put("UserId", savedAdminDto.getUserId());
		response.put("Username", savedAdminDto.getUserName());

		return CustomHttpResponse.responseBuilder("Admin User has been created", HttpStatus.CREATED, response);
	}

	@GetMapping("{username}")
	public ResponseEntity<Object> adminUserByUsername(@PathVariable("username") String username) {
		AdminDto user = adminService.getAdminByUsername(username);
		Map<String, Object> response = new HashMap<>();
		response.put("UserId", user.getUserId());
		response.put("Username", user.getUserName());
		response.put("Status", user.isActive());
		response.put("Role", user.getUserRole());
		return CustomHttpResponse.responseBuilder("Admin :", HttpStatus.FOUND, response);
	}

	@PostMapping("/update-admin-user/{username}")
	public ResponseEntity<Object> updateAdminUser(@Valid @PathVariable String username,
			@RequestBody UpdateAdminDto dto) {
		Admin admin = adminService.updateAdminUser(dto, username);
		return CustomHttpResponse.responseBuilder("user updated successfully", HttpStatus.OK, admin);
	}

	@PostMapping("/get-phonenumbersof-admins-for-restcall")
	public List<String> getPhoneNumbersOfAdmins(@RequestBody List<String> adminids) {
		List<Admin> cordinators = adminService.getPhoneNumbersOfAdmins(adminids);
		List<String> phone_numbers = new ArrayList<>();
		for (Admin admin : cordinators) {
			phone_numbers.add(admin.getPhone_number());
		}
		return phone_numbers;
	}

	@PostMapping("/get-coordinatorby-linkedzone-id/{linkedZoneId}")
	public ResponseEntity<Object> getCoordinatorByLinkedZoneID(@PathVariable String linkedZoneId) {
		return CustomHttpResponse.responseBuilder("Get Admin by linkedzone", HttpStatus.OK,
				adminService.getCoordinatorByLinkedZoneID(linkedZoneId));
	}

	@GetMapping("/getall-admin-users")
	public ResponseEntity<Object> getAllAdminUsers() {
		return CustomHttpResponse.responseBuilder("getAll admins", HttpStatus.OK, adminService.getAllAdminUsers());
	}

	/*
	 * (1) : Use by SuperAdmin for coordinator's password, (2) Use by Coordinator to
	 * reset ssuser's password
	 */
	@PostMapping("/reset-password/{userName}")
	public ResponseEntity<Object> resetPassword(@PathVariable String userName, @RequestParam String password) {
		return CustomHttpResponse.responseBuilder("Password reset sucessfully", HttpStatus.OK,
				adminService.resetPassword(userName, password));
	}

	/* only for coordinator use, to change own known password */
	@PostMapping("/change-password/{userName}")
	public ResponseEntity<Object> changePassword(@PathVariable String userName,
			@RequestBody AdminChangePasswordDto dto) {
		String status = adminService.changePassword(userName, dto);
		if (status == null)
			return CustomHttpResponse.responseBuilder(status, HttpStatus.OK, userName);
		return CustomHttpResponse.responseBuilder(status, HttpStatus.OK, userName);
	}

	@PostMapping("/block-unblock-admin/{id}")
	public ResponseEntity<Object> blockAndUnblockAdmin(@PathVariable String id) {
		String response = adminService.blockAndUnblockAdmin(id);
		return CustomHttpResponse.responseBuilder(response, HttpStatus.OK, id);
	}
}
