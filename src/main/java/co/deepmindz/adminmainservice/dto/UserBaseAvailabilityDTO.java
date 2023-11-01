package co.deepmindz.adminmainservice.dto;

import lombok.*;

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
