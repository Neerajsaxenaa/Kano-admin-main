package co.deepmindz.adminmainservice.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.deepmindz.adminmainservice.dto.EditLiteralDto;
import co.deepmindz.adminmainservice.models.Languages;
import co.deepmindz.adminmainservice.models.Literals;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.valueObj;

public class LiteralUtils {
    private String id;
    private String value;

    public LiteralUtils(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
    public static EditLiteralDto mapEntityToRequestDto(Literals literal, List<Languages> languages) {

    	Map<String, String> idWithLanguageNameMap = new HashMap<>();
    	for(Languages lang: languages)
    		idWithLanguageNameMap.put(String.valueOf( lang.getLanguageID()), lang.getLanguaeName());
    	
    	EditLiteralDto responseDto = new EditLiteralDto(); 
    	responseDto.setLiteralID(literal.getLiteralID());
    	responseDto.setNewLiteral(literal.getLiteral());
    	List<valueObj> list = new ArrayList<>();
    	for(String litral : literal.getLiteralsinAllSupportedLanguage()) {
    		String[] tok = litral.split(":");
    		list.add(new valueObj(idWithLanguageNameMap.get(tok[0]),tok[1], ""));    		
    	}
    	responseDto.setLiterals(list.toArray(new valueObj[list.size()]));
    	
    	return responseDto;
    }
    
}
