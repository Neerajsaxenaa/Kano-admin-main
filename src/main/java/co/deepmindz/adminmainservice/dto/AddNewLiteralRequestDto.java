package co.deepmindz.adminmainservice.dto;

import co.deepmindz.adminmainservice.utils.CustomDataTypes.valueObj;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class AddNewLiteralRequestDto {

	private String newLiteral;

	private valueObj[] literals;

	public AddNewLiteralRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddNewLiteralRequestDto(String newLiteral, valueObj[] literals) {
		super();
		this.newLiteral = newLiteral;
		this.literals = literals;
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
