package co.deepmindz.adminmainservice.controllers;

/*author : ram kumar*/

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.deepmindz.adminmainservice.dto.AddNewLiteralRequestDto;
import co.deepmindz.adminmainservice.dto.EditLiteralDto;
import co.deepmindz.adminmainservice.dto.LoginModeStatusDto;
import co.deepmindz.adminmainservice.exception.ResourceAlreadyExist;
import co.deepmindz.adminmainservice.exception.ResourceNotFoundException;
import co.deepmindz.adminmainservice.models.Languages;
import co.deepmindz.adminmainservice.models.Literals;
import co.deepmindz.adminmainservice.models.RequestLanguageData;
import co.deepmindz.adminmainservice.models.Resources;
import co.deepmindz.adminmainservice.resources.CustomConfigHttpResponse;
import co.deepmindz.adminmainservice.resources.CustomHttpResponse;
import co.deepmindz.adminmainservice.services.LanguageService;
import co.deepmindz.adminmainservice.services.LoginModeService;
import co.deepmindz.adminmainservice.services.ResourceService;
import co.deepmindz.adminmainservice.services.ThemeService;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.keyValueObj;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.themes;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.valueObj;
import co.deepmindz.adminmainservice.utils.LiteralUtils;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin-main/language")
public class LanguageController {

	@Autowired
	LanguageService languageService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	LoginModeService loginModeService;
	
	@Autowired
	ThemeService themeService;

	// Will return the all literals in all supported languages
	@GetMapping("/get-configurations")
	public ResponseEntity<Object> primary() {
		LoginModeStatusDto currentLoginModeStatus = loginModeService.getCurrentConfig();
		List<valueObj> result = languageService.getSupportedLanguageList();
		Map<String, String> appStatics = getStringStringMap();
		return CustomConfigHttpResponse.responseBuilder("All Configurations ", HttpStatus.OK, currentLoginModeStatus,
				result, appStatics);
	}

	private Map<String, String> getStringStringMap() {
		List<Resources> items = resourceService.getResources();

		Map<String, String> appStatics = new HashMap<>();
		for (Resources resource : items) {
			String type = resource.getType();
			String url = resource.getUrl();

			// Check if the type and url are not null or empty
			if (type != null && !type.isEmpty() && url != null && !url.isEmpty()) {
				appStatics.put(type, url);
			}
		}
		return appStatics;
	}

	// All language with language name and literals in that particular languages
	@PostMapping("/add-language")
	public ResponseEntity<Object> addLanguage(@RequestBody RequestLanguageData newLanguage) throws IOException {
		if (languageService.isLanguageAlreadySupported(newLanguage)) {
			throw new ResourceAlreadyExist("Literals already available in : " + newLanguage.getNewLanguage());
		}

		languageService.addLanguage(newLanguage);

		return CustomHttpResponse.responseBuilder("New Language has been updated: ", HttpStatus.CREATED, newLanguage);
	}

	// Will return all supported languages only
	@GetMapping("/get-all-language")
	public ResponseEntity<Object> getSupportedLanguageList() {
		List<valueObj> langs = languageService.getSupportedLanguageList();

		return CustomHttpResponse.responseBuilder("All Supported Languages: ", HttpStatus.OK, langs);
	}

	// For Mobile consumers
	// Will return the literals in selected languages only
	@GetMapping(value = "/get-selected-languages/{selectedLanguages}")
	public ResponseEntity<Object> getLiteralsInSelectedLangugages(@PathVariable String[] selectedLanguages) {
		List<keyValueObj> result = languageService.getLiteralsInSelectedLangauge(selectedLanguages);

		return CustomHttpResponse.responseBuilder("Literals in Selected Languages ", HttpStatus.OK, result);
	}

	@GetMapping("/get-all-literals")
	public ResponseEntity<Object> getAllPredefinedLiteras() {
		List<LiteralUtils> predefinedLiterals = languageService.getAllPredefinedLiteralsAsLanguageLiteral();

		return CustomHttpResponse.responseBuilder("All Literals ", HttpStatus.OK, predefinedLiterals);
	}

	@PostMapping("/add-literal")
	public ResponseEntity<Object> addNewLiteral(@RequestBody AddNewLiteralRequestDto newLiteral) {
		if (languageService.isLiteralAlreadyExists(newLiteral)) {
			throw new ResourceAlreadyExist(
					"LiteralId already Exists: " + newLiteral.getNewLiteral().toLowerCase().replaceAll("\\s+", ""));
		}

		Literals addLiteral = languageService.addNewLiteral(newLiteral);
		return CustomHttpResponse.responseBuilder("New Literal has been added ", HttpStatus.OK, addLiteral);
	}

	@PostMapping("/delete-literal")
	public ResponseEntity<Object> deleteLiteral(@RequestParam("literalID") String literalID) {
		Literals literal = languageService.findByLiteralID(literalID);
		if (literal == null) {
			throw new ResourceNotFoundException(literalID, literalID, "");
		}

		languageService.deleteLiteral(literal);
		return CustomHttpResponse.responseBuilder("Literal has been Deleted ", HttpStatus.OK, literal);
	}

	@PostMapping("/edit-literal")
	public ResponseEntity<Object> editLiteral(@Valid @RequestBody EditLiteralDto modifiedLiteral) {
		Literals literal = languageService.findByLiteralID(modifiedLiteral.getLiteralID());
		if (literal == null) {
			throw new ResourceNotFoundException(modifiedLiteral.getLiteralID(), modifiedLiteral.getLiteralID(), "");
		}
		languageService.editLiteral(modifiedLiteral);
		return CustomHttpResponse.responseBuilder("Literal has been updated ", HttpStatus.OK, modifiedLiteral);
	}

	@GetMapping("/get-literal")
	public ResponseEntity<Object> getLiteralByID(@Valid @RequestParam String literalID) {
		Literals literal = languageService.findByLiteralID(literalID);
		if (literal == null) {
			throw new ResourceNotFoundException(literalID, literalID, "");
		}

		EditLiteralDto literalDto = LiteralUtils.mapEntityToRequestDto(literal, languageService.getAllLanguages());
		return CustomHttpResponse.responseBuilder("Literal with literal_id :", HttpStatus.OK, literalDto);
	}

	@PostMapping("/delete-language")
	public ResponseEntity<Object> deleteLanguage(@RequestParam("languageID") String languageID) {
		Languages language = languageService.findByLanguageID(languageID.toLowerCase());
		if (language == null) {
			throw new ResourceNotFoundException(languageID, languageID, "");
		}

		languageService.deleteLanguage(language);
		return CustomHttpResponse.responseBuilder("Language has been Deleted ", HttpStatus.OK, language);
	}

}
