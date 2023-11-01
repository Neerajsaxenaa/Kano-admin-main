package co.deepmindz.adminmainservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.deepmindz.adminmainservice.dto.LoginRequestDto;
import co.deepmindz.adminmainservice.resources.CustomHttpResponse;
import co.deepmindz.adminmainservice.services.AdminService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin-main/auth")
@CrossOrigin
public class Auth {
    @Autowired
    AdminService adminService;
    @PostMapping("/login")
    public ResponseEntity<Object> createAdminUser(@Valid @RequestBody LoginRequestDto loginRequest) {
        LoginRequestDto loginRequestDto = adminService.loginAdmin(loginRequest);
        return  CustomHttpResponse.responseBuilder("Admin User has been created", HttpStatus.CREATED, "done");
    }
}
