package co.deepmindz.adminmainservice.services;

import java.util.List;

import co.deepmindz.adminmainservice.dto.AddNewLiteralRequestDto;
import co.deepmindz.adminmainservice.dto.EditLiteralDto;
import co.deepmindz.adminmainservice.models.Languages;
import co.deepmindz.adminmainservice.models.Literals;
import co.deepmindz.adminmainservice.models.RequestLanguageData;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.keyValueObj;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.valueObj;
import co.deepmindz.adminmainservice.utils.LiteralUtils;

public interface LanguageService {

	public List<keyValueObj> getDefaultLiterals();
	
	public boolean isLanguageAlreadySupported(RequestLanguageData newLanguageData);
	
	public void addLanguage( RequestLanguageData dto);
	
	public List<valueObj> getSupportedLanguageList();
	
	public List<keyValueObj> getLiteralsInSelectedLangauge(String[] languages);
	
	public String[] getAllPredefinedLiteras();
	
	public boolean isLiteralAlreadyExists(AddNewLiteralRequestDto newLiteral);
	
	public Literals addNewLiteral(AddNewLiteralRequestDto newLiteral);

    List<LiteralUtils> getAllPredefinedLiteralsAsLanguageLiteral();
    
    public Literals findByLiteralID(String literal);

    public void deleteLiteral(Literals literalID);
    
    public Languages findByLanguageID(String languageID);
    
    public void deleteLanguage(Languages langaugeID);
    
    public void editLiteral(EditLiteralDto dto);
    
    public void cleanAllLanguageLiterals();
    
    public List<Languages> getAllLanguages();
    
}
