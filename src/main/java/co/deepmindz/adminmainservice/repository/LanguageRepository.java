package co.deepmindz.adminmainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import co.deepmindz.adminmainservice.models.Languages;

@Component
public interface LanguageRepository extends JpaRepository<Languages, Integer> {

	public Languages findByLanguaeName(String id);

}
