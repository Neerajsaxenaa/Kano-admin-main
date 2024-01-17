package co.deepmindz.adminmainservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserBaseAvailabilityDTO {
    private boolean external_availability;
    private String message;
    private String message_code;

}
