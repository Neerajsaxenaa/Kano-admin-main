package co.deepmindz.adminmainservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Languages {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer languageID;		

	@Column(unique = true)
	private String languaeName;
	
	private String languageInNative;

	public Languages() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Languages(Integer languageID, String languaeName, String languageInNative) {
		super();
		this.languageID = languageID;
		this.languaeName = languaeName;
		this.languageInNative = languageInNative;
	}

	public Integer getLanguageID() {
		return languageID;
	}

	public void setLanguageID(Integer languageID) {
		this.languageID = languageID;
	}

	public String getLanguaeName() {
		return languaeName;
	}

	public void setLanguaeName(String languaeName) {
		this.languaeName = languaeName;
	}

	public String getLanguageInNative() {
		return languageInNative;
	}

	public void setLanguageInNative(String languageInNative) {
		this.languageInNative = languageInNative;
	}
	
	
	
}
