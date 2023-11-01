package co.deepmindz.adminmainservice.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigurationManagementDto {
    @NotEmpty(message = "Service name should be available")
    private String serviceName;

    private List<Map<String, Object>> subServices;

    private String status;

}
