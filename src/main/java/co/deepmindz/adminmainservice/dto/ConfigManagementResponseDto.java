package co.deepmindz.adminmainservice.dto;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConfigManagementResponseDto {
	
	private String managementId;
	private String serviceId;
	private String subService;
	private String orgServiceManagement;

}
