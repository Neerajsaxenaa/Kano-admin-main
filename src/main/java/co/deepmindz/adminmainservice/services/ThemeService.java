package co.deepmindz.adminmainservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.ChangeThemesRequestDto;
import co.deepmindz.adminmainservice.utils.CustomDataTypes.themes;

@Service
public interface ThemeService {

	public List<themes> getCurrentThemeSetting();
	
	public List<themes> getAllThemesLiterals();
	
	public List<themes> changeThemes(ChangeThemesRequestDto changeThemeDto);
	
	public List<themes> addNewThemes(ChangeThemesRequestDto addNewThemesDtos);
}
