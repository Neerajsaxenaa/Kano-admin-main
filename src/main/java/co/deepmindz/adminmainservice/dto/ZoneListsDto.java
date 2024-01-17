package co.deepmindz.adminmainservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ZoneListsDto {
	private String _id;
	private String name;
	private String linked_zone;
	private String code;
	private String belongs_to;

}
