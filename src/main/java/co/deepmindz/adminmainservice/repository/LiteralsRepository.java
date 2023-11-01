package co.deepmindz.adminmainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.deepmindz.adminmainservice.models.Literals;
import jakarta.transaction.Transactional;

public interface LiteralsRepository extends JpaRepository<Literals, Integer> {

	public Literals findByLiteralID(String literalID);
	
	@Transactional
	@Modifying
	@Query("update Literals s SET s.literalsinAllSupportedLanguage = :literalInAllSupportedLangs WHERE s.id = :literalID")
	public void updateLiteralsWithNewLanguage(@Param("literalInAllSupportedLangs") String[] literalInAllSupportedLangs,
			@Param("literalID") String literalID);

	@Transactional
	@Modifying
	@Query("update Literals s SET s.literal = :updatedLiteral WHERE s.id = :literalID")
	public void updateLiteral(@Param("updatedLiteral") String updatedLiteral,
			@Param("literalID") String literalID);
}
