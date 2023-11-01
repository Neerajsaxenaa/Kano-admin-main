package co.deepmindz.adminmainservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "LoginMode")
public class LoginMode {

	@Id
	private String loginmode; // 2FA, Credential

	private String configuration;
	
	private boolean status;

	public LoginMode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginMode(String loginmode, String configuration, boolean status) {
		super();
		this.loginmode = loginmode;
		this.configuration = configuration;
		this.status = status;
	}

	public String getLoginmode() {
		return loginmode;
	}

	public void setLoginmode(String loginmode) {
		this.loginmode = loginmode;
	}

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
