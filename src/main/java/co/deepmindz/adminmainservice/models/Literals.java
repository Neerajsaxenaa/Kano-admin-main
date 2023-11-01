package co.deepmindz.adminmainservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Literals {
	
	@Id
	private String literalID;

	private String literal;

	private String[] literalsinAllSupportedLanguage;

	public Literals() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Literals(String literalID, String literal, String[] literalsinAllSupportedLanguage) {
		super();
		this.literalID = literalID;
		this.literal = literal;
		this.literalsinAllSupportedLanguage = literalsinAllSupportedLanguage;
	}

	public String getLiteralID() {
		return literalID;
	}

	public void setLiteralID(String literalID) {
		this.literalID = literalID;
	}

	public String getLiteral() {
		return literal;
	}

	public void setLiteral(String literal) {
		this.literal = literal;
	}

	public String[] getLiteralsinAllSupportedLanguage() {
		return literalsinAllSupportedLanguage;
	}

	public void setLiteralsinAllSupportedLanguage(String[] literalsinAllSupportedLanguage) {
		this.literalsinAllSupportedLanguage = literalsinAllSupportedLanguage;
	}
	
	
}
