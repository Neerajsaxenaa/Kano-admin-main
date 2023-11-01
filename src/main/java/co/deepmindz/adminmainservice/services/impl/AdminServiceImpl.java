package co.deepmindz.adminmainservice.services.impl;

import co.deepmindz.adminmainservice.dto.AdminDto;
import co.deepmindz.adminmainservice.dto.LoginRequestDto;
import co.deepmindz.adminmainservice.exception.ResourceAlreadyExist;
import co.deepmindz.adminmainservice.exception.ResourceNotFoundException;
import co.deepmindz.adminmainservice.mapper.AutoAdminMapper;
import co.deepmindz.adminmainservice.models.Admin;
import co.deepmindz.adminmainservice.repository.AdminRepository;
import co.deepmindz.adminmainservice.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public String generateRandomUserId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    @Override
    public AdminDto createAdmin(AdminDto adminDto) {
        Optional<Admin> optionalAdmin = adminRepository.findByUserName(adminDto.getUserName());
        if(optionalAdmin.isPresent()){
            throw new ResourceAlreadyExist("Admin User Already Exist");
        }
        adminDto.setUserId(generateRandomUserId());
        Admin admin = AutoAdminMapper.MAPPER.mapToAdmin(adminDto);
        Admin savedAdmin = adminRepository.save(admin);
        AdminDto adminSavedDto = AutoAdminMapper.MAPPER.mapToAdminDto(savedAdmin);
        return adminSavedDto;
    }

    @Override
    public AdminDto getAdminById(String userName) {
        Admin admin = adminRepository.findByUserName(userName).orElseThrow(
                () -> new ResourceNotFoundException("User","Id", userName)
        );
        return AutoAdminMapper.MAPPER.mapToAdminDto(admin);
     }

    @Override
    public LoginRequestDto loginAdmin(LoginRequestDto loginRequestDto) {
        return loginRequestDto;
    }
}
