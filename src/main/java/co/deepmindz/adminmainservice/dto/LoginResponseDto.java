package co.deepmindz.adminmainservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
private String access_token;
private String refresh_token;
private String expires_in;
private String refresh_expires_in;
private String token_type;
}
