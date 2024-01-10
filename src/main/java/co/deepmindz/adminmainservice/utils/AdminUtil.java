package co.deepmindz.adminmainservice.utils;

import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.AdminDto;
import co.deepmindz.adminmainservice.dto.UpdateAdminDto;
import co.deepmindz.adminmainservice.models.Admin;
import jakarta.validation.Valid;

@Service
public class AdminUtil {

	public Admin mapDtoToEntity(@Valid UpdateAdminDto dto, AdminDto user) {
		return new Admin(user.getUserId(), user.getUserName(), dto.getEmail(), user.getLinked_zone(), dto.getPhone_number(),
				user.getPassword(), user.getUserRole(), user.getStatus(), user.getCreatedAt(), user.getActions());
	}

}
