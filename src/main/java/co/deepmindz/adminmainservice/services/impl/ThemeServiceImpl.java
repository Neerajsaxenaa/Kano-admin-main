package co.deepmindz.adminmainservice.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.ChangeThemesRequestDto;
import co.deepmindz.adminmainservice.models.Themes;
import co.deepmindz.adminmainservice.repository.ThemeRepository;
import co.deepmindz.adminmainservice.services.ThemeService;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.themes;

@Service
public class ThemeServiceImpl implements ThemeService {

	@Autowired
	ThemeRepository themeRepository;

	public List<themes> mapEntityListToResponseDto(List<Themes> allThemes, boolean onlyLiterals) {
		List<themes> responseThemes = new ArrayList<>();
		for (Themes theme : allThemes) {
			if (onlyLiterals)
				responseThemes.add(new themes(theme.getLiteral_id(), theme.getDescription(), "", theme.getImageURL()));
			else
				responseThemes.add(new themes(theme.getLiteral_id(), theme.getDescription(), theme.getColor(),
						theme.getImageURL()));
		}
		return responseThemes;
	}

	public List<themes> getCurrentThemeSetting() {
		return mapEntityListToResponseDto(themeRepository.findAll(), false);
	}

	public List<themes> getAllThemesLiterals() {
		return mapEntityListToResponseDto(themeRepository.findAll(), true);
	}

	public List<themes> changeThemes(ChangeThemesRequestDto changeThemeDto) {
		List<Themes> updatedThemes = new ArrayList<>();
		for (themes dto : changeThemeDto.getThemes()) {
			updatedThemes.add(new Themes(dto.key, dto.description, dto.color, dto.url));
		}
		return mapEntityListToResponseDto(themeRepository.saveAll(updatedThemes), false);
	}

	public List<themes> addNewThemes(ChangeThemesRequestDto addNewThemesDtos) {
		List<Themes> newThemes = new ArrayList<>();
		for (themes dto : addNewThemesDtos.getThemes()) {
			newThemes.add(new Themes(dto.key, dto.description, dto.color, dto.url));
		}
		return mapEntityListToResponseDto(themeRepository.saveAll(newThemes), false);
	}
}
