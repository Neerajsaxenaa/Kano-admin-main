package co.deepmindz.adminmainservice.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.deepmindz.adminmainservice.dto.AdminDto;
import co.deepmindz.adminmainservice.dto.UpdateAdminDto;
import co.deepmindz.adminmainservice.models.Admin;
import co.deepmindz.adminmainservice.resources.CustomHttpResponse;
import co.deepmindz.adminmainservice.services.AdminService;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.CordinatorIds;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin-main")
@CrossOrigin
public class AdminUserManagement {
	@Autowired
	private AdminService adminService;

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
		AdminDto user = adminService.userByUsername(username);
		Map<String, Object> response = new HashMap<>();
		response.put("UserId", user.getUserId());
		response.put("Username", user.getUserName());
		response.put("Status", user.getStatus());
		response.put("Role", user.getUserRole());
		return CustomHttpResponse.responseBuilder("Admin :", HttpStatus.FOUND, response);
	}

	@PostMapping("/update-admin-user/{username}")
	public ResponseEntity<Object> updateAdminUser(@Valid @PathVariable String username,
			@RequestBody UpdateAdminDto dto) {

		AdminDto user = adminService.userByUsername(username);
		boolean password = BCrypt.checkpw(dto.getPassword(), user.getPassword());
		Admin updateAdminUser = null;
		if (!password)
			return CustomHttpResponse.responseBuilder("Invalid password..!!", HttpStatus.OK, updateAdminUser);
		updateAdminUser = adminService.updateAdminUser(dto, user);
		return CustomHttpResponse.responseBuilder("Admin User successfully updated", HttpStatus.OK, updateAdminUser);

	}

	@PostMapping("/get-mobile-by-cordinator")
	public ResponseEntity<Object> getMobileNoByCordinatorIds(@RequestBody CordinatorIds cordinatorIds) {
		List<Admin> cordinators = adminService.getMobileNoByCordinatorIds(cordinatorIds);
		List<Map<String, String>> phone_numbers = new ArrayList<>();
		for (Admin admin : cordinators) {
			Map<String, String> usersWithLinkedZones = new HashMap<>();
			usersWithLinkedZones.put("phone_numbers", admin.getPhone_number());
			phone_numbers.add(usersWithLinkedZones);
		}
		return CustomHttpResponse.responseBuilder("All cordinators phone_number", HttpStatus.OK, phone_numbers);
	}

}
