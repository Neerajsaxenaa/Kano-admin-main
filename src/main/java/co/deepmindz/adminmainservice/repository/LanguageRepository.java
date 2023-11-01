package co.deepmindz.adminmainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import co.deepmindz.adminmainservice.models.Languages;
import jakarta.transaction.Transactional;

@Component
public interface LanguageRepository extends JpaRepository<Languages, Integer> {

	public Languages findByLanguaeName(String id);

}
