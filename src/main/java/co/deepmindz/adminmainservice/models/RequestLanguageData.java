package co.deepmindz.adminmainservice.models;

import co.deepmindz.adminmainservice.utils.CustomDataTypes.keyValuePair;


//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class RequestLanguageData {
	private String newLanguage;
	private String newLanguageInNative;
	private keyValuePair[] literals;
	public RequestLanguageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RequestLanguageData(String newLanguage, String newLanguageInNative, keyValuePair[] literals) {
		super();
		this.newLanguage = newLanguage;
		this.newLanguageInNative = newLanguageInNative;
		this.literals = literals;
	}
	public String getNewLanguage() {
		return newLanguage;
	}
	public void setNewLanguage(String newLanguage) {
		this.newLanguage = newLanguage;
	}
	public String getNewLanguageInNative() {
		return newLanguageInNative;
	}
	public void setNewLanguageInNative(String newLanguageInNative) {
		this.newLanguageInNative = newLanguageInNative;
	}
	public keyValuePair[] getLiterals() {
		return literals;
	}
	public void setLiterals(keyValuePair[] literals) {
		this.literals = literals;
	} 
	
	
}
