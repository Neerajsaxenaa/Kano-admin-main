package co.deepmindz.adminmainservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginModeDto {

	private String mode;
	
	private boolean status;
	
	private String config;
	
	
//	public static List<LoginMode> populateLoginMode() {
//		LoginMode fa = new LoginMode("Two_FA", "+91,10", true);
//		LoginMode cred = new LoginMode("Credential", "+91:10", false);
//		
//		return List.of(fa, cred);
//	}
}
