package co.deepmindz.adminmainservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExternalReourceResponseDTO {
	private String id;
    private String type;
    private String description;
    private String resource;
    private String maintained_by;
    private String create_at;
}
