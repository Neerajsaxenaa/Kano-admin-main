package co.deepmindz.adminmainservice.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.AddNewLiteralRequestDto;
import co.deepmindz.adminmainservice.dto.EditLiteralDto;
import co.deepmindz.adminmainservice.models.Languages;
import co.deepmindz.adminmainservice.models.Literals;
import co.deepmindz.adminmainservice.models.RequestLanguageData;
import co.deepmindz.adminmainservice.repository.LanguageRepository;
import co.deepmindz.adminmainservice.repository.LiteralsRepository;
import co.deepmindz.adminmainservice.services.LanguageService;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.keyValueObj;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.keyValuePair;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.valueObj;
import co.deepmindz.adminmainservice.utils.LanguageUtil;
import co.deepmindz.adminmainservice.utils.LiteralUtils;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private LiteralsRepository literalsRepository;

	@Autowired
	private LanguageUtil languageUtil;

	@Override
	public List<keyValueObj> getDefaultLiterals() {
		List<Literals> allLiterals = literalsRepository.findAll();
		List<Languages> allLanguages = languageRepository.findAll();
		return languageUtil.mapToLanguageResponseData(allLiterals, allLanguages, null);
	}

	public boolean isLanguageAlreadySupported(RequestLanguageData newLanguageData) {
		boolean languageAlreadySupported = false;
		for (Languages language : languageRepository.findAll()) {
			if (language.getLanguaeName().toLowerCase().equals(newLanguageData.getNewLanguage().toLowerCase())) {
				languageAlreadySupported = true;
			}
		}
		return languageAlreadySupported;
	}

	public void populateMap(List<Literals> allLiterals, Map<String, Literals> literalIDWithLiteralMap) {
		for (Literals literal : allLiterals) {
			literalIDWithLiteralMap.put(literal.getLiteralID(), literal);
		}
	}

	@Override
	public void addLanguage(RequestLanguageData newLanguageData) {
		Map<String, Literals> literalIDWithLiteralMap = new HashMap<>();
		List<Literals> allLiterals = literalsRepository.findAll();
		populateMap(allLiterals, literalIDWithLiteralMap);

		Languages newLanObj = new Languages();
		newLanObj.setLanguaeName(newLanguageData.getNewLanguage().toLowerCase());
		newLanObj.setLanguageInNative(newLanguageData.getNewLanguageInNative());
//		newLanObj.setLanguageID(2);

		Languages newLanguage = languageRepository.save(newLanObj);
		List<Literals> literalsList = new ArrayList<>();
		for (keyValuePair pair : newLanguageData.getLiterals()) {

			if (literalIDWithLiteralMap.get(pair.key) != null) {
				List<String> literalInAllLangs = new ArrayList<>();
				literalInAllLangs
						.addAll(List.of(literalIDWithLiteralMap.get(pair.key).getLiteralsinAllSupportedLanguage()));
				literalInAllLangs.add(newLanguage.getLanguageID() + ":" + pair.value);
				literalsRepository.updateLiteralsWithNewLanguage(
						literalInAllLangs.toArray(new String[literalInAllLangs.size()]), pair.key);
			} else {
				Literals obj = new Literals();
				obj.setLiteralID(pair.key);
				obj.setLiteral(pair.value);
				obj.setLiteralsinAllSupportedLanguage(new String[] { newLanguage.getLanguageID() + ":" + pair.value });
				literalsList.add(obj);
			}
		}
		if (!literalsList.isEmpty())
			literalsRepository.saveAll(literalsList);
	}

	public List<valueObj> getSupportedLanguageList() {
		List<valueObj> allsupportedLanguages = new ArrayList<>();
		for (Languages lang : languageRepository.findAll()) {
			allsupportedLanguages.add(
					new valueObj(lang.getLanguageID().toString(), lang.getLanguaeName(), lang.getLanguageInNative()));
		}
		return allsupportedLanguages;
	}

	public List<keyValueObj> getLiteralsInSelectedLangauge(String[] selectedLanguages) {
		List<Literals> allSupportedLangaugaeLiterals = literalsRepository.findAll();
		List<Languages> allLanguages = languageRepository.findAll();
		if (allLanguages == null || allSupportedLangaugaeLiterals == null)
			return null;
		return languageUtil.mapToLanguageResponseData(allSupportedLangaugaeLiterals, allLanguages, selectedLanguages);
	}

	public String[] getAllPredefinedLiteras() {
		List<String> allPredefinedLiterals = new ArrayList<>();
		List<Literals> allSupportedLangaugaeLiterals = literalsRepository.findAll();
		for (Literals dbRow : allSupportedLangaugaeLiterals) {
			allPredefinedLiterals.add(dbRow.getLiteralID() + ":" + dbRow.getLiteral());
		}
		return allPredefinedLiterals.toArray(new String[allPredefinedLiterals.size()]);
	}

	public boolean isLiteralAlreadyExists(AddNewLiteralRequestDto newLiteral) {
		List<Literals> langs = literalsRepository.findAll();
		boolean alreadyExists = false;
		for (Literals lang : langs) {
			if (lang.getLiteralID().equals(newLiteral.getNewLiteral().toLowerCase().replaceAll("\\s+", ""))) {
				alreadyExists = true;
			}
		}
		return alreadyExists;
	}

	public String getLiteralIdFromLiteral(String literalName) {
		return literalName.toLowerCase().replaceAll("\\s+", "");
	}

	public Literals addNewLiteral(AddNewLiteralRequestDto newLiteral) {

		List<Languages> allLanguages = languageRepository.findAll();
		Map<String, Languages> languageIDMap = new HashMap<>();
		for (Languages lang : allLanguages)
			languageIDMap.put(lang.getLanguaeName(), lang);

		Literals newLitrl = new Literals();
		newLitrl.setLiteralID(getLiteralIdFromLiteral(newLiteral.getNewLiteral()));
		newLitrl.setLiteral(newLiteral.getNewLiteral());

		List<String> newliteralInAllLanguage = new ArrayList<>();
		for (valueObj obj : newLiteral.getLiterals()) {
			newliteralInAllLanguage.add(languageIDMap.get(obj.lang).getLanguageID() + ":" + obj.value);
		}

		newLitrl.setLiteralsinAllSupportedLanguage(
				newliteralInAllLanguage.toArray(new String[newliteralInAllLanguage.size()]));

		return literalsRepository.save(newLitrl);
	}

	@Override
	public List<LiteralUtils> getAllPredefinedLiteralsAsLanguageLiteral() {
		List<Literals> allLiterals = literalsRepository.findAll();
		List<LiteralUtils> predefinedLiteralsList = new ArrayList<>();

		for (Literals dbRow : allLiterals) {
			LiteralUtils entry = new LiteralUtils(dbRow.getLiteralID(), dbRow.getLiteral());
			predefinedLiteralsList.add(entry);
		}

		return predefinedLiteralsList;
	}

	@Override
	public Literals findByLiteralID(String literalID) {
		return literalsRepository.findByLiteralID(literalID);
	}

	@Override
	public void deleteLiteral(Literals literal) {
		literalsRepository.delete(literal);
	}

	@Override
	public Languages findByLanguageID(String languageID) {
		return languageRepository.findByLanguaeName(languageID);
	}

	@Override
	public void deleteLanguage(Languages language) {
		List<Literals> allLiterals = literalsRepository.findAll();
		for (Literals literal : allLiterals) {
			List<String> updatedLiteralsAfterDelete = new ArrayList<>();
			for (String supportedLangLiteral : literal.getLiteralsinAllSupportedLanguage()) {
				if (language.getLanguageID().toString().trim().equals(supportedLangLiteral.split(":")[0].trim()))
					continue;
				updatedLiteralsAfterDelete.add(supportedLangLiteral);
			}
			literalsRepository.updateLiteralsWithNewLanguage(
					updatedLiteralsAfterDelete.toArray(new String[updatedLiteralsAfterDelete.size()]),
					literal.getLiteralID());
		}
		languageRepository.delete(language);
	}

	@Override
	public void editLiteral(EditLiteralDto literalDto) {
		List<Languages> langList = languageRepository.findAll();
		Map<String, Integer> langIDWithLangNameMap = new HashMap<>();
		for (Languages lang : langList)
			langIDWithLangNameMap.put(lang.getLanguaeName(), lang.getLanguageID());

		Map<Integer, String> changedLiteralsMap = new HashMap<>();
		for (valueObj keyVal : literalDto.getLiterals()) {
			changedLiteralsMap.put(langIDWithLangNameMap.get(keyVal.lang), keyVal.value);
		}

		Literals literals = literalsRepository.findByLiteralID(literalDto.getLiteralID());

		List<String> updatedLiterals = new ArrayList<>();
		for (String literal : literals.getLiteralsinAllSupportedLanguage()) {
			if (changedLiteralsMap.get(Integer.valueOf(literal.split(":")[0])) != null) {
				updatedLiterals.add(
						literal.split(":")[0] + ":" + changedLiteralsMap.get(Integer.valueOf(literal.split(":")[0])));
			} else {
				updatedLiterals.add(literal);
			}
		}
		literalsRepository.updateLiteralsWithNewLanguage(updatedLiterals.toArray(new String[updatedLiterals.size()]),
				literalDto.getLiteralID());

		literalsRepository.updateLiteral(literalDto.getNewLiteral(), literalDto.getLiteralID());
	}

	public void cleanAllLanguageLiterals() {
		Map<String, String> idWithLangugaeNameMap = new HashMap<>();
		List<Languages> languages = languageRepository.findAll();
		for (Languages lang : languages) {
			idWithLangugaeNameMap.put(lang.getLanguageID().toString().trim(), lang.getLanguaeName());
			if (!lang.getLanguaeName().toLowerCase().equals("english")) {
				languageRepository.delete(lang);
			}
		}

		List<Literals> allLiterals = literalsRepository.findAll();
		for (Literals literal : allLiterals) {
			List<String> onlyEnglishLiterats = new ArrayList<>();
			for (String langLiteral : literal.getLiteralsinAllSupportedLanguage()) {
				if (idWithLangugaeNameMap.get(langLiteral.split(":")[0]) != null
						&& idWithLangugaeNameMap.get(langLiteral.split(":")[0]).equals("english")) {
					onlyEnglishLiterats.add(langLiteral);
				}
				literalsRepository.updateLiteralsWithNewLanguage(
						onlyEnglishLiterats.toArray(new String[onlyEnglishLiterats.size()]), literal.getLiteralID());
			}
		}
	}

	public List<Languages> getAllLanguages() {
		return languageRepository.findAll();
	}
}
