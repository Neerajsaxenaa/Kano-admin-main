package co.deepmindz.adminmainservice.dto;

import co.deepmindz.adminmainservice.utils.CustomDataTypes.valueObj;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditLiteralDto {

	private String literalID;

	private String newLiteral;

	private valueObj[] literals;

	public String getLiteralID() {
		return literalID;
	}

	public void setLiteralID(String literalID) {
		this.literalID = literalID;
	}

	public String getNewLiteral() {
		return newLiteral;
	}

	public void setNewLiteral(String newLiteral) {
		this.newLiteral = newLiteral;
	}

	public valueObj[] getLiterals() {
		return literals;
	}

	public void setLiterals(valueObj[] literals) {
		this.literals = literals;
	}
	
}
