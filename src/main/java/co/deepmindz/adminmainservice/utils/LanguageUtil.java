package co.deepmindz.adminmainservice.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.models.Languages;
import co.deepmindz.adminmainservice.models.Literals;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.keyValueObj;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.valueObj;

@Service
public class LanguageUtil {
	
	public static List<keyValueObj> mapToLanguageResponseData(List<Literals> literals, List<Languages> allLanguages,
			String[] selectedLangs) {

		Map<Integer, Languages> languageIDMap = new HashMap<>();
		for(Languages lang : allLanguages)
			languageIDMap.put(lang.getLanguageID(), lang);
		
		Set<String> langsSet = new HashSet<>();
		if (selectedLangs != null) {
			for(String str: selectedLangs)
				langsSet.add(str);
		}

		List<keyValueObj> dataList = new ArrayList<keyValueObj>();

		Map<String, Map<String, List<valueObj>>> tempdata = new HashMap<String, Map<String, List<valueObj>>>();
		tempdata.put("data", new HashMap<String, List<valueObj>>());
		for (Literals dbRow : literals) {
			for (String str : dbRow.getLiteralsinAllSupportedLanguage()) {
				String[] langLiteral = str.split(":");
				if (selectedLangs != null && !langsSet.contains(languageIDMap.get(Integer.valueOf(langLiteral[0])) != null ?
						languageIDMap.get(Integer.valueOf(langLiteral[0])).getLanguaeName(): 
							null))
					continue;
				if (tempdata.get("data").get(dbRow.getLiteralID()) == null) {
					tempdata.get("data").put(dbRow.getLiteralID(), new ArrayList<>());
					tempdata.get("data").get(dbRow.getLiteralID()).add(
							new valueObj(langLiteral[0],languageIDMap.get(Integer.valueOf(langLiteral[0])).getLanguaeName(),langLiteral[1]));
							
				} else {
					tempdata.get("data").get(dbRow.getLiteralID()).add(
							new valueObj(langLiteral[0],languageIDMap.get(Integer.valueOf(langLiteral[0])).getLanguaeName(), langLiteral[1]));
				}
			}
		}

		for (Entry<String, List<valueObj>> entry : tempdata.get("data").entrySet()) {
			dataList.add(new keyValueObj(entry.getKey(), entry.getValue()));
		}
		return dataList;
	}
}
