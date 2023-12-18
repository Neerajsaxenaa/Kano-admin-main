package co.deepmindz.adminmainservice.controllers;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.deepmindz.adminmainservice.dto.AdminDto;
import co.deepmindz.adminmainservice.resources.CustomHttpResponse;
import co.deepmindz.adminmainservice.services.AdminService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin-main/language")
//@CrossOrigin
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
        response.put("name",savedAdminDto.getName());
        response.put("Username",savedAdminDto.getUserName());
        response.put("UserId",savedAdminDto.getUserId());
        
        
        return  CustomHttpResponse.responseBuilder("Admin User has been created", HttpStatus.CREATED, response);
    }
    @GetMapping("{id}")
    public ResponseEntity<Object> getAdminUser(@PathVariable("id") String adminId, @RequestHeader("loggedInUser") List username) {
        AdminDto savedAdminDto = adminService.getAdminById(adminId);
        Map<String, Object> response = new HashMap<>();
        response.put("UserId",savedAdminDto.getUserId());
        response.put("Username",savedAdminDto.getUserName());
        response.put("Status",savedAdminDto.getStatus());
        response.put("Role",savedAdminDto.getUserRole());
        return  CustomHttpResponse.responseBuilder("Admin :", HttpStatus.FOUND, response);
    }

}
