package co.deepmindz.adminmainservice.utils;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;

public class Templates {
	public enum LOGINMODES {
		Two_FA, USER_CREDENTIALS
	}

	public enum VISITTYPES {
		Team_Visit, Individual_Visit
	}

	public enum LOGO_TYPES {
		splash_screen, login_screen
	}
	
	public enum CONFIGURATION {
		org_hierarchy, admin_main
	}
	
	public enum TEAMMEMBERSLIMIT {
		ISS_STATE, ISS_ZONAL, ISS_LGA
	}

	public static ParameterizedTypeReference<Map<String, String>> responseTypeForRestAPICall = new ParameterizedTypeReference<>() {
	};

	public enum ALLSERVICES {
		admin_main {
			public String toString() {
				return "http://admin-main-service";
			}
		},

		admin_org {
			public String toString() {
				return "http://admin-org-hierarchy-service";
			}
		},

		visit_service {
			public String toString() {
				return "http://ss-visit-service";
			}
		}
	}
}
