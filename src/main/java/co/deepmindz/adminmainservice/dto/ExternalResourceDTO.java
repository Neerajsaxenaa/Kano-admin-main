package co.deepmindz.adminmainservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExternalResourceDTO {
    private String type;
    private String description;
    private String resource;
    private String maintained_by;
}
