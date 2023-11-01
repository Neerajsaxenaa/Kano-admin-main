package co.deepmindz.adminmainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import co.deepmindz.adminmainservice.models.LoginMode;
import jakarta.transaction.Transactional;

@Component
public interface LoginModeRepository extends JpaRepository<LoginMode, Integer> {

	@Transactional
	@Modifying
	@Query("update LoginMode s SET s.configuration = :config, s.status= true WHERE s.loginmode = :mode")
	public void updateLoginModeStatus( @Param("mode") String mode, @Param("config") String config);
	
	@Transactional
	@Modifying
	@Query("update LoginMode s SET s.configuration = :config, s.status= false WHERE s.loginmode = :mode")
	public void resetLoginMode( @Param("mode") String mode, @Param("config") String config);
}
