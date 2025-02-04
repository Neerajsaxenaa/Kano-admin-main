package co.deepmindz.adminmainservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import co.deepmindz.adminmainservice.models.Resources;

@Repository
public interface ResourceRepo extends JpaRepository<Resources, String> {

	@Query("SELECT r FROM Resources r WHERE r.type IN ('splash_screen', 'login_screen')")
	List<Resources> getStaticAppResources();

	
  @Query("SELECT r FROM Resources r WHERE r.type = :type")
	Resources findByType(@RequestParam String type);
	
}
