package co.deepmindz.adminmainservice.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseData {

	private String language;

	private List<Map.Entry<String, String>> Literals;

	public ResponseData() {
		this.Literals = new ArrayList<>();
	}

}
