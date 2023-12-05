package co.deepmindz.adminmainservice.models;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConfigurationManagement {
	

	@Id
	@UuidGenerator
	private String managementId;
	private String serviceId;
	private String subService;
	
	
	private Boolean  orgServiceStatus;
	

	public String getManagementId() {
		return managementId;
	}

	public void setManagementId(String managementId) {
		this.managementId = managementId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getSubService() {
		return subService;
	}

	public void setSubService(String subService) {
		this.subService = subService;
	}

	public boolean isOrgServiceStatus() {
		return orgServiceStatus;
	}

	public void setOrgServiceStatus(boolean orgServiceStatus) {
		this.orgServiceStatus = orgServiceStatus;
	}
	

}
