package co.deepmindz.adminmainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import co.deepmindz.adminmainservice.models.Configuration;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, String> {

	@Query("select c from Configuration c where c.configuration = :getConfiguration")
	Configuration findCurrentStatusOfConfig(@RequestParam String getConfiguration);
}
