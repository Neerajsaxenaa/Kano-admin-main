package co.deepmindz.adminmainservice.mapper;

import co.deepmindz.adminmainservice.dto.AdminDto;
import co.deepmindz.adminmainservice.models.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoAdminMapper  {
    AutoAdminMapper MAPPER = Mappers.getMapper(AutoAdminMapper.class);
    @Mapping(source = "role", target = "userRole")
    AdminDto mapToAdminDto(Admin admin);

    Admin mapToAdmin(AdminDto adminDto);
}
